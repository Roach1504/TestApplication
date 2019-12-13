// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.3")
        classpath(kotlin("gradle-plugin", "1.3.41"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.0-rc03")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}
