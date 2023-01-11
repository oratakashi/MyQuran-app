import com.android.build.gradle.*

plugins {
    id("java-platform")
    id("maven-publish")
}

group = "com.oratakashi"
version = "1.0.0"

subprojects {
    group = "com.oratakashi"

    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-parcelize")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "com.google.devtools.ksp")

    plugins.withType(com.android.build.gradle.BasePlugin::class.java).configureEach {
        configure<BaseExtension> {
            namespace = "com.oratakashi.myquran.modules.$name"
            compileSdkVersion(33)
            defaultConfig {
                minSdk = 22
                targetSdk = 33
                versionCode = 1
                versionName = project.version.toString()

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }
            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isDebuggable = true
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isDebuggable = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            buildFeatures.apply {
                viewBinding = true
                buildConfig = false
            }
            lintOptions.apply {
                disable("Instantiatable")
                isAbortOnError = false
            }
        }
    }

    dependencies {
    }
}

dependencies {
    constraints {
//        api(project(":core:uikit"))
    }
}