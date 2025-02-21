// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("com.google.dagger.hilt.android") version "2.49" apply false
    id ("androidx.navigation.safeargs") version "2.8.7" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.6.0-M1" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}