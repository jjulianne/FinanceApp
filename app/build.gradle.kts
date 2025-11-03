plugins {
    // Plugins base (Android Application y Kotlin)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Plugins necesarios
    alias(libs.plugins.kotlin.compose) // Compilador de Compose
    alias(libs.plugins.devtools.ksp)     // Procesamiento de simbolos de Kotlin (para Hilt/Room)
    alias(libs.plugins.hilt)     // Plugin de Hilt
    alias(libs.plugins.kotlin.serialization) // Plugin para Kotlinx Serialization
}


android {
    namespace = "com.example.financeApp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.financeApp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        // Runner necesario para las pruebas con Hilt
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    // Configuracion de Compose
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // CORE ANDROID Y COMPOSE
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)

    // Iconos Material
    implementation(libs.compose.material.icons)

    // NAVEGACION
    implementation(libs.androidx.navigation.compose)
    // Integracion de Hilt con Navigation
    implementation(libs.hilt.navigation.compose)

    // INYECCION DE DEPENDENCIAS (HILT)
    implementation(libs.hilt)           // Biblioteca principal de Hilt
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.datastore.preferences)
    ksp(libs.hilt.compiler)             // Procesador de anotaciones de Hilt (KSP)

    // PERSISTENCIA DE DATOS (ROOM)
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)               // Extensiones KTX para Room
    ksp(libs.room.compiler)                     // Procesador de anotaciones de Room (KSP)

    // SERIALIZACION
    implementation(libs.serialization)          // Kotlinx Serialization

    // PRUEBAS (TESTS)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dependencias de pruebas de arquitectura
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.truth)

}