package app.atori.multi.viewModels

import androidx.lifecycle.ViewModel
import app.atori.multi.databases.MessageDao
import app.atori.multi.entities.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.delay.packet.DelayInformation
import org.jxmpp.jid.impl.JidCreate
import java.util.*

class XmppViewModel(
    private val messageDao: MessageDao,
) : ViewModel() {
    val messages: Flow<List<MessageEntity>> = messageDao.getAllMessages()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private var connection: XMPPConnection? = null

    fun connectToXmpp(username: String, password: String, domain: String = DEFAULT_DOMAIN) {
        CoroutineScope(Dispatchers.IO).launch {
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

                startListeningForMessages()
            } catch (e: Exception) {
                _isLoggedIn.value = false
            }
        }
    }

    init {
        login("ezc", "j1N2i2U4evER@co")
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
            connectToXmpp(username, password, domain)
            // 记录登录
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            // 退出登录
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
            chatManager.addIncomingListener { from, message, _ ->
                val delayInfo = message.getExtensionElement(
                    DelayInformation.ELEMENT,
                    DelayInformation.NAMESPACE
                ) as? DelayInformation
                val timestamp = delayInfo?.stamp ?: Date(19, 7, 10)

                println("$timestamp: Received from $from:\n${message.body}")
                addMessage(from.asUnescapedString(), message.body, timestamp.time)
            }
        }
    }

    companion object {
        const val DEFAULT_DOMAIN = "suchat.org"
    }
}