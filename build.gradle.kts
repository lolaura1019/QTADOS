// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Añade el plugin de Safe Args aquí
    id("androidx.navigation.safeargs") version "2.7.7" apply false
}