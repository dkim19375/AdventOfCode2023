import me.dkim19375.dkimgradle.enums.mavenAll
import me.dkim19375.dkimgradle.util.setupJava

plugins {
    application
    kotlin("jvm") version "1.9.21"
    id("org.cadixdev.licenser") version "0.6.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.github.dkim19375.dkim-gradle") version "1.3.7"
}

group = "me.dkim19375"
version = "1.0.0"

setupJava(
    mainClassName = "me.dkim19375.adventofcode2023.AdventOfCode2023Kt",
    javaVersion = JavaVersion.VERSION_17,
)

repositories {
    mavenAll()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.1.0")

    implementation("io.github.dkim19375:dkimcore:1.4.2")
}

tasks {
    named<JavaExec>("run") {
        standardInput = System.`in`
    }

    build {
        dependsOn(licenseFormat)
    }
}