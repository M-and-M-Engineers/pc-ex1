plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:4.2.5")
    implementation("io.vertx:vertx-web:4.2.5")
    implementation("io.vertx:vertx-web-client:4.2.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.register<JavaExec>("scm"){
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("scm.SmartCoffeeMachineService")
}

tasks.register<JavaExec>("user"){
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("user.Application")
}