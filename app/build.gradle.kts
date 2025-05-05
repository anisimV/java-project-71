plugins {
    id("application")
    id("java")
    id("checkstyle") // Подключаем Checkstyle Plugin
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("hexlet.code.App")
}

// Настроим путь к checkstyle.xml
checkstyle {
    toolVersion = "10.12.3" // Последняя версия на момент 2024 года
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
}
