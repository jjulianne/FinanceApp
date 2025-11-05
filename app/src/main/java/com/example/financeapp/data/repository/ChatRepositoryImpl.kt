package com.example.financeapp.data.repository

import com.example.financeApp.R
import com.example.financeapp.domain.repository.Chat
import com.example.financeapp.domain.repository.ChatData
import com.example.financeapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor() : ChatRepository {

    // Simulación de una base de datos en memoria con datos falsos
    private val fakeChatData = ChatData(
        activeChats = listOf(
            Chat(
                icon = R.drawable.help_white,
                title = "Support Assistant",
                lastMessage = "Hello! I'm here to assist you",
                timestamp = "2 Min Ago",
                isActive = true
            )
        ),
        endedChats = listOf(
            Chat(
                icon = R.drawable.help_white,
                title = "Help Center",
                lastMessage = "Your account is ready to use...",
                timestamp = "Feb 08 -2024",
                isActive = false
            ),
            Chat(
                icon = R.drawable.help_white,
                title = "Support Assistant",
                lastMessage = "Hello! I'm here to assist you",
                timestamp = "Dic 24 -2023",
                isActive = false
            ),
            Chat(
                icon = R.drawable.help_white,
                title = "Support Assistant",
                lastMessage = "Hello! I'm here to assist you",
                timestamp = "Sep 10 -2023",
                isActive = false
            ),
            Chat(
                icon = R.drawable.help_white,
                title = "Help Center",
                lastMessage = "Hi, how are you today?",
                timestamp = "June 12 -2023",
                isActive = false
            )
        )
    )

    // Usamos un StateFlow para simular un Flow de DataStore o Room
    private val _chatFlow = MutableStateFlow(fakeChatData)

    override fun getChats(): Flow<ChatData> {
        return _chatFlow.asStateFlow()
    }
}