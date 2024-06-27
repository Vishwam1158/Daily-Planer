// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {

    // The following section is needed only if pluginMangement is not used in settings.gradle
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.newrelic.agent.android:agent-gradle-plugin:7.4.1")
    }
}
