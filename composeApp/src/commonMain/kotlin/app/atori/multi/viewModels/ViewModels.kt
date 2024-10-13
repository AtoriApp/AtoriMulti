package app.atori.multi.viewModels

import androidx.lifecycle.ViewModel
import app.atori.multi.databases.AtoriDatabase
import app.atori.multi.entities.AccountEntity
import app.atori.multi.entities.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.chat2.IncomingChatMessageListener
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.delay.packet.DelayInformation
import org.jxmpp.jid.impl.JidCreate
import java.util.*

class XmppViewModel(
    private val atoriDatabase: AtoriDatabase,
) : ViewModel() {
    private val messageDao = atoriDatabase.getMessageDao()
    private val accountDao = atoriDatabase.getAccountDao()

    val messages: Flow<List<MessageEntity>> = messageDao.getAllMessages()
    val accounts: Flow<List<AccountEntity>> = accountDao.getAllAccounts()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _noAccount = MutableStateFlow(false)
    val noAccount: StateFlow<Boolean> get() = _noAccount

    private var connection: XMPPConnection? = null
    private var msgListener: IncomingChatMessageListener =
        IncomingChatMessageListener { from, message, _ ->
            val delayInfo = message.getExtensionElement(
                DelayInformation.ELEMENT,
                DelayInformation.NAMESPACE
            ) as DelayInformation?
            val timestamp = delayInfo?.stamp ?: Date(19, 7, 10)

            println("$timestamp: Received from $from:\n${message.body}")
            addMessage(from.asUnescapedString(), message.body, timestamp.time)
        }

    fun connectToXmpp(
        username: String,
        password: String,
        domain: String = DEFAULT_DOMAIN,
        callback: (Boolean) -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            println("Login for Jid: $username@$domain Pwd: $password")

            try {
                val config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(username, password)
                    .setXmppDomain(domain)
                    .build()

                connection = XMPPTCPConnection(config).apply {
                    connect()
                    login()
                }

                _isLoggedIn.value = true
                callback(true)

                startListeningForMessages()
            } catch (e: Exception) {
                _isLoggedIn.value = false
                println("Failed to connect $username: ${e.message}")
            }
        }
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            accounts.collect { accounts ->
                println("Accounts Flushed: ${accounts.size}")
                if (accounts.isNotEmpty()) {
                    if (!_isLoggedIn.value) {
                        println("Do login")
                        val account = accounts.first()
                        login(account.jid, account.password)
                    } else {
                        println("Already logged in")
                        _noAccount.value = false
                    }
                } else _noAccount.value = true
            }
        }
    }

    fun sendMessage(toUser: String, messageBody: String) {
        CoroutineScope(Dispatchers.IO).launch {
            connection?.let {
                val chatManager = ChatManager.getInstanceFor(it)
                val chat = chatManager.chatWith(JidCreate.entityBareFrom(toUser))
                chat.send(messageBody)
            }
        }
    }

    fun login(username: String, password: String, domain: String = DEFAULT_DOMAIN) {
        CoroutineScope(Dispatchers.IO).launch {
            connectToXmpp(username, password, domain) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (it) {
                        _noAccount.value = false
                        val accountEntity = AccountEntity(jid = username, password = password)
                        accountDao.insertAccount(accountEntity)
                    }
                }
            }
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            disconnectToXmpp()
        }
    }

    private fun disconnectToXmpp() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                stopListeningForMessages()
                connection = null

                _isLoggedIn.value = false
            } catch (e: Exception) {
                println("Failed to disconnect: ${e.message}")
            }
        }
    }

    private fun stopListeningForMessages() {
        connection?.let {
            val chatManager = ChatManager.getInstanceFor(it)
            chatManager.removeIncomingListener(msgListener)
        }
    }

    private fun addMessage(fromUser: String, messageBody: String, timestamp: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val messageEntity = MessageEntity(
                fromUser = fromUser,
                messageBody = messageBody,
                timestamp = timestamp
            )
            messageDao.insertMessage(messageEntity)
        }
    }

    private fun startListeningForMessages() {
        connection?.let {
            val chatManager = ChatManager.getInstanceFor(it)
            chatManager.addIncomingListener(msgListener)
        }
    }

    companion object {
        const val DEFAULT_DOMAIN = "suchat.org"
    }
}