import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

application {
    mainClassName = "com.team1091.pid.PidPracticeKt"
}

group = "com.team1091.pid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("org.processing:core:3.3.6")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}