import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
    id("de.mannodermaus.android-junit5")
    id("com.github.ben-manes.versions").version(Versions.dependencyUpdates)
    kotlin("plugin.serialization").version(Versions.kotlin)
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.thecodeside.githubclient"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.jetpackCompose
    }
    kotlin.sourceSets.all {
        languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=org.mylibrary.OptInAnnotation"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Compose
    implementation("androidx.compose.ui:ui:${Versions.jetpackCompose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.jetpackCompose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.jetpackCompose}")
    implementation("androidx.compose.material:material:${Versions.jetpackCompose}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}")

    // Material design icons
    implementation("androidx.compose.material:material-icons-core:${Versions.jetpackCompose}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.jetpackCompose}")

    implementation("androidx.navigation:navigation-compose:${Versions.navigationCompose}")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")

    implementation("androidx.core:core-ktx:${Versions.androidxCore}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")

    //hilt
    implementation("com.google.dagger:hilt-android:${Versions.hiltCore}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hiltCore}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}")

    //network
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serializationConverter}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.jsonSerialization}")

    // coil
    implementation("io.coil-kt:coil:${Versions.coil}")

    //utils
    implementation("com.jakewharton.timber:timber:${Versions.timber}")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}")

    //tests
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit5}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.junit5}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}")
    testImplementation("com.github.thecodeside:timber-junit5-extension:${Versions.timberJunit5}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("io.kotest:kotest-assertions-core:${Versions.kotest}")
    testImplementation("app.cash.turbine:turbine:${Versions.turbine}")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.jetpackCompose}")
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}