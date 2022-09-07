import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun deleteChat() {
        ChatService.add(1, Message("Тест 1"))
        assertTrue(ChatService.deleteChat(1))
    }

    @Test(expected = NotFoundException::class)
    fun deleteChat_exception() {
        ChatService.deleteChat(100)
    }

    @Test
    fun deleteMes() {
        ChatService.add(1, Message("Тест 1"))
        ChatService.add(1, Message("Тест 2"))
        assertTrue(ChatService.deleteMes(1, 2))
    }

    @Test(expected = NotFoundException::class)
    fun deleteMes_exception_idUser() {
        ChatService.add(1, Message("Тест 1"))
        ChatService.add(1, Message("Тест 2"))
        assertTrue(ChatService.deleteMes(100, 1))
    }

    @Test(expected = NotFoundException::class)
    fun deleteMes_exception_idMes() {
        ChatService.add(1, Message("Тест 1"))
        ChatService.add(1, Message("Тест 2"))
        assertTrue(ChatService.deleteMes(1, 200))
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.add(1, Message("Тест 1"))
        ChatService.add(2, Message("Тест 2"))
        val result = ChatService.getUnreadChatsCount()
        assertEquals(2, result)
    }

    @Test
    fun getMes() {
        ChatService.add(1, Message("Тест 1"))
        ChatService.add(2, Message("Тест 2"))
        ChatService.getMes(1,1)
    }

    @Test(expected = NotFoundException::class)
    fun getMes_exception() {
        ChatService.getMes(100,1)
    }
}