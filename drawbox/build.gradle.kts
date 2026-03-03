
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.compose)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
//    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
//    id("convention-publication")
}

group = Library.group
version = Library.version

kotlin {
    androidLibrary{
        namespace = "io.github.markyav.drawbox"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
//            jvmTarget = JvmTarget.JVM_1_8
            jvmTarget = JvmTarget.JVM_17
        }
//        publishLibraryVariants("release")
    }
    jvm("desktop")
    sourceSets {
        commonMain.dependencies {
//            api(compose.runtime)
//            api(compose.foundation)

//            implementation(libs.compose.components.resources)
            implementation(libs.compose.foundation)
//            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
//            implementation(libs.compose.ui)
//            implementation(libs.compose.uiToolingPreview)
        }
    }
}
