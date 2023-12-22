import me.dkim19375.dkimgradle.util.setJavaVersion

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.github.dkim19375.dkim-gradle") version "1.3.7"
}

group = "me.dkim19375"

setJavaVersion(JavaVersion.VERSION_17)

repositories {
    mavenCentral()
}