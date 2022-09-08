import java.lang.RuntimeException

fun main() {
    ChatService.add(1, Message("Тест 1"))
    ChatService.add(1, Message("Тест 2"))
    ChatService.add(2, Message("Тест 3"))
    ChatService.add(2, Message("Тест 4"))
    ChatService.add(1, Message("Тест 5"))
    ChatService.add(3, Message("Тест 6"))
    println()
    ChatService.getChats()
    println()
    ChatService.toPrint()

    println(ChatService.getMes(1, 1))

    println(ChatService.getUnreadChatsCount())
    println(ChatService.deleteChat(1))
    println()
    println(ChatService.deleteMes(3, 1))
    println()
    ChatService.toPrint()

}

data class Chat(
    val message: MutableList<Message> = mutableListOf()
)

data class Message(
    val text: String,
    var read: Boolean = false
)

class NotFoundException(msg: String) : RuntimeException(msg)

object ChatService {
    private var chats = mutableMapOf<Int, Chat>()

    fun add(id_user: Int, mes: Message) {
        chats.getOrPut(id_user) { Chat() }.message.add(mes)
    }

    fun deleteChat(id_user: Int): Boolean {
        chats.remove(id_user) ?: throw NotFoundException("Чат с id равным $id_user не найден")
        return true
    }

        fun deleteMes(id_user: Int, id_mes: Int): Boolean {
            val chat = chats[id_user] ?: throw NotFoundException("Чат с id равным $id_user не найден")
            if (chat.message.size == 1) chats.remove(id_user)
            else {
                if (chat.message.size >= id_mes - 1) {
                    chat.message.removeAt(id_mes - 1)
                } else throw NotFoundException("Сообщение с id равным $id_mes не найдено")
            }
            return true
        }

    fun getUnreadChatsCount(): Int {
        return chats.values.count { chat -> chat.message.any { !it.read } }
    }

    fun getMes(id_user: Int, count: Int): List<Message> =
        chats[id_user]
            .let { it?.message ?: throw NotFoundException("Чат с id равным $id_user не найден") }
            .takeLast(count)
            .onEach { it.read = true }
            .toList()


    fun getChats() {
        chats.forEach { (id_user, chat) -> println("$id_user=${chat.message.last()}") }
    }

    fun toPrint() {
        chats.forEach(::println)
    }
}

