import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


description = "Weight Reductor - app to track calories"
group = "com.spyrdonapps"
// Align with Spring Version
version = "2.2.5.RELEASE"

java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    val kotlinVersion = "1.3.61"
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.google.cloud.tools.jib") version "1.3.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

val boostrapVersion = "3.3.6"
val jQueryVersion = "2.2.4"
val jQueryUIVersion = "1.11.4"

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("javax.cache:cache-api")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:jquery:$jQueryVersion")
    implementation("org.webjars:jquery-ui:$jQueryUIVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    runtimeOnly("org.hsqldb:hsqldb")
    runtimeOnly("mysql:mysql-connector-java")

    // todo development only
    implementation("org.springframework.boot:spring-boot-devtools")

    implementation("org.thymeleaf.extras:thymeleaf-extras-java8time")
}

// TODO is this needed or relevant? maybe it will break something?
jib {
    to {
        image = "spyrdonapps/weight-reductor-spring"
        tags = setOf(project.version.toString(), "latest")
    }
}
