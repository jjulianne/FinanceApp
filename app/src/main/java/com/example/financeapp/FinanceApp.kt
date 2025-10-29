package com.example.financeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt necesita un punto de entrada para toda la app.
// Esta es nuestra clase Application personalizada.
@HiltAndroidApp
class FinanceApp : Application() {

}