plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "com.simonorono"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.0.0")
    implementation("org.json:json:20231013")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.simonorono.aoc2015.MainKt")
}
