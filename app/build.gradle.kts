plugins {
    id("application")
    id("java")
    id("checkstyle") // Подключаем Checkstyle Plugin

    id("org.sonarqube") version "6.0.1.5171"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("hexlet.code.App")
}

sonar {
    properties {
        property("sonar.projectKey", "anisimV_java-project-71")
        property("sonar.organization", "anisimv")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

// Настроим путь к checkstyle.xml
checkstyle {
    toolVersion = "10.12.3" // Последняя версия на момент 2024 года
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
}
