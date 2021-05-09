plugins {
    java
    id("maven-publish")
    war
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.spring.io/libs-release")
}

dependencies {
    // Wicket
    implementation("org.apache.wicket:wicket-core:8.12.0")
    implementation("org.apache.wicket:wicket-auth-roles:8.12.0")
    implementation("org.apache.wicket:wicket-spring:8.12.0")
    implementation("org.apache.wicket:wicket-extensions:8.12.0")
    implementation("org.apache.wicket:wicket-devutils:8.12.0")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Utils
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // Databases
    runtimeOnly("org.hsqldb:hsqldb:2.5.2")
    runtimeOnly("mysql:mysql-connector-java:8.0.24")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // JPA
    annotationProcessor("org.hibernate:hibernate-jpamodelgen")
}

group = "at.dru"
version = "0.0.1-SNAPSHOT"
description = "at.dru.wicketblog"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["web"])
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootRun {
    main = "at.dru.wicketblog.WicketWebApplication"
}
