plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.simonorono"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.simonorono.advent_of_code.MainKt")
}