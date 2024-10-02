package test.multi.services

import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.chat2.IncomingChatMessageListener
import org.jivesoftware.smack.packet.MessageBuilder
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import test.multi.utils.XmppUtils.jid

class XMPPService {
    private var connection: AbstractXMPPConnection? = null

    fun connect(username: String, password: String, serviceName: String) {
        val config = XMPPTCPConnectionConfiguration.builder()
            .setUsernameAndPassword(username, password)
            .setXmppDomain(serviceName)
            .build()

        connection = XMPPTCPConnection(config)
        connection!!.connect()!!.login()
    }

    fun sendMessage(to: String, messageBody: String) {
        val chatManager = ChatManager.getInstanceFor(connection)

        val chat = chatManager.chatWith(to.jid.asEntityBareJidOrThrow())

        val messageBuilder = MessageBuilder.buildMessage()
        messageBuilder.body = messageBody
        chat.send(messageBuilder.build())
    }

    fun addIncomingMessageListener(listener: IncomingChatMessageListener) {
        val chatManager = ChatManager.getInstanceFor(connection)
        chatManager.addIncomingListener(listener)
    }

    fun disconnect() {
        connection?.disconnect()
    }
}