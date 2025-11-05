package com.example.financeapp.ui.screens.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeApp.R
import com.example.financeapp.domain.model.TransactionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    // 1. Estado para Totales y Cabecera (HomeHeader / TotalStatCard)
    private val _totalBalance = MutableStateFlow("$0.00")
    val totalBalance: StateFlow<String> = _totalBalance.asStateFlow()

    private val _totalExpense = MutableStateFlow("$0.00")
    val totalExpense: StateFlow<String> = _totalExpense.asStateFlow()

    private val _expenseLimit = MutableStateFlow(0.0f)
    val expenseLimit: StateFlow<Float> = _expenseLimit.asStateFlow()

    private val _limitAmount = MutableStateFlow("$20,000.00")
    val limitAmount: StateFlow<String> = _limitAmount.asStateFlow()


    // 2. Estado para SavingsAndMetricCard
    private val _savingsGoalProgress = MutableStateFlow(0.5f)
    val savingsGoalProgress: StateFlow<Float> = _savingsGoalProgress.asStateFlow()

    private val _savingsIconId = MutableStateFlow(R.drawable.car_green)
    val savingsIconId: StateFlow<Int> = _savingsIconId.asStateFlow()

    private val _metric1Title = MutableStateFlow("Revenue Last Week")
    val metric1Title: StateFlow<String> = _metric1Title.asStateFlow()

    private val _metric1IconId = MutableStateFlow(R.drawable.salary_green)
    val metric1IconId: StateFlow<Int> = _metric1IconId.asStateFlow()

    private val _metric1Amount = MutableStateFlow("$0.00")
    val metric1Amount: StateFlow<String> = _metric1Amount.asStateFlow()

    private val _metric2Title = MutableStateFlow("Food Last Week")
    val metric2Title: StateFlow<String> = _metric2Title.asStateFlow()

    private val _metric2IconId = MutableStateFlow(R.drawable.food_green)
    val metric2IconId: StateFlow<Int> = _metric2IconId.asStateFlow()

    private val _metric2Amount = MutableStateFlow("$0.00")
    val metric2Amount: StateFlow<String> = _metric2Amount.asStateFlow()


    // 3. Estado para Transacciones
    private val _transactions = MutableStateFlow<List<TransactionItem>>(emptyList())
    val transactions: StateFlow<List<TransactionItem>> = _transactions.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {

            // TOTALES Y CABECERA
            _totalBalance.value = "$7,783.00"
            _totalExpense.value = "-$1,187.40"
            _expenseLimit.value = 0.3f

            // SAVINGS AND METRICS
            _metric1IconId.value = R.drawable.salary_green
            _metric1Amount.value = "$4.000,00"
            _metric2IconId.value = R.drawable.food_green
            _metric2Amount.value = "-$100.00"


            // TRANSACCIONES

            val mockTransactions = listOf(

                TransactionItem("1", R.drawable.salary_white, "Salary", "18:27 - April 30", "Monthly", "$4.000,00", false, R.color.light_blue),

                TransactionItem("2", R.drawable.groceries, "Groceries", "17:00 - April 24", "Pantry", "-$100,00", true, R.color.vivid_blue),

                TransactionItem("3", R.drawable.rent, "Rent", "8:30 - April 15", "Rent", "-$674,40", true, R.color.ocean_blue),
            )
            _transactions.value = mockTransactions

            _isLoading.value = false
        }
    }
}