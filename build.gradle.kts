plugins {
    java
    id("maven-publish")
    war
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.cyclonedx.bom") version "3.2.2"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.spring.io/libs-release")
}

dependencies {
    // Wicket
    implementation("org.apache.wicket:wicket-core")
    implementation("org.apache.wicket:wicket-auth-roles")
    implementation("org.apache.wicket:wicket-spring")
    implementation("org.apache.wicket:wicket-extensions")
    implementation("org.apache.wicket:wicket-devutils")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Utils
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.google.guava:guava:33.5.0-jre")

    // Databases
    runtimeOnly("com.h2database:h2:2.4.240")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.5.7")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // JPA
    annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:7.2.4.Final")
}

dependencyManagement {
    dependencies {
        dependencySet("org.apache.wicket:10.8.0") {
            entry("wicket-core")
            entry("wicket-auth-roles")
            entry("wicket-spring")
            entry("wicket-extensions")
            entry("wicket-devutils")
        }
    }
}

group = "at.dru"
version = "0.0.1-SNAPSHOT"
description = "at.dru.wicketblog"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
    mainClass.set("at.dru.wicketblog.WicketWebApplication")
}

// tasks.cyclonedxBom {
//     setProjectType("application")
//     setOutputName("bom")
//     setOutputFormat("json")
//     setIncludeBomSerialNumber(false)
//     setIncludeLicenseText(true)
//     setComponentVersion("2.0.0")
// }
