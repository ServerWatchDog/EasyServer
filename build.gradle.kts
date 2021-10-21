val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
}

group = "watch.i"
version = "0.0.1"
application {
    mainClass.set("i.watch.Application")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:2.7.4")
    implementation("org.ktorm:ktorm-core:3.4.1")
    implementation("org.ktorm:ktorm-support-mysql:3.4.1")

    implementation("com.viartemev:ktor-flyway-feature:1.3.0") {
        exclude("ch.qos.logback", "logback-classic")
    }
    implementation("com.zaxxer:HikariCP:5.0.0")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("com.github.OpenEdgn.Logger4KSupport:slf4j-over-logger4k:1.0.1-via-2.1")
    implementation("com.github.OpenEdgn.Logger4K:logger-console:2.1.0")
    implementation("com.github.OpenEdgn.Logger4K:logger-core:2.1.0")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version") {
        exclude("ch.qos.logback", "logback-classic")
    }
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.7.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
