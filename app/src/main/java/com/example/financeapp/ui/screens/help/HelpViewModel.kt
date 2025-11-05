package com.example.financeapp.ui.screens.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// import com.example.financeapp.domain.repository.HelpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    // private val helpRepository: HelpRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HelpUiState())
    val uiState: StateFlow<HelpUiState> = _uiState.asStateFlow()

    init {
        loadFaqs()
    }

    /**
     * Carga la lista completa de FAQs (simulado).
     */
    private fun loadFaqs() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500) // Simular carga

            val mockFaqs = listOf(
                FaqItem(1, "How to use FinWise?", "Lorem ipsum dolor sit amet...", "General"),
                FaqItem(2, "How much does it cost?", "Lorem ipsum dolor sit amet...", "Services"),
                FaqItem(3, "How to contact support?", "Lorem ipsum dolor sit amet...", "Account"),
                FaqItem(4, "How can I reset my password?", "Lorem ipsum dolor sit amet...", "Account"),
                FaqItem(5, "Are there any privacy measures?", "Lorem ipsum dolor sit amet...", "General"),
                FaqItem(6, "Can I customize settings?", "Lorem ipsum dolor sit amet...", "Account"),
                FaqItem(7, "How can I delete my account?", "Lorem ipsum dolor sit amet...", "Account"),
                FaqItem(8, "How do I access my expense history?", "Lorem ipsum dolor sit amet...", "Services"),
                FaqItem(9, "Can I use the app offline?", "Lorem ipsum dolor sit amet...", "General")
            )

            _uiState.update { it.copy(allFaqs = mockFaqs, isLoading = false) }
            // Aplicamos el filtro inicial
            filterList()
        }
    }

    /**
     * Filtra la lista 'allFaqs' basado en los estados actuales
     * y actualiza 'filteredFaqs'.
     */
    private fun filterList() {
        _uiState.update { state ->
            val filteredByCategory = state.allFaqs.filter {
                it.category == state.selectedCategory
            }

            val filteredBySearch = if (state.searchQuery.isBlank()) {
                filteredByCategory
            } else {
                filteredByCategory.filter {
                    it.question.contains(state.searchQuery, ignoreCase = true) ||
                            it.answer.contains(state.searchQuery, ignoreCase = true)
                }
            }

            state.copy(filteredFaqs = filteredBySearch)
        }
    }

    fun onTabSelected(tab: String) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun onCategoryChange(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
        filterList() // Vuelve a filtrar
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        filterList() // Vuelve a filtrar
    }
}