buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        dependencies {
            classpath("com.android.tools.build:gradle:${Versions.gradleBuildTool}")
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
            classpath("de.mannodermaus.gradle.plugins:android-junit5:${Versions.junit5Plugin}")
            classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltCore}")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}