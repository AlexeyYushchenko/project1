plugins {
	java
	war
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id ("io.freefair.lombok") version "8.0.1"
}

group = "utlc.ru"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
//	implementation("org.springframework.boot:spring-boot-starter-jdbc") I don't think I'll need it
	implementation("org.springframework.boot:spring-boot-starter-mail")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-client") I will need it later
//	implementation("org.springframework.boot:spring-boot-starter-security") I will need it later
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.liquibase:liquibase-core")

	implementation("org.springframework.kafka:spring-kafka")

//	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6") I will need it later

//	developmentOnly("org.springframework.boot:spring-boot-devtools") what is that for?
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	runtimeOnly("org.postgresql:postgresql")

	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<JavaCompile> {
	options.compilerArgs = listOf("-Amapstruct.defaultComponentModel=spring")
	options.isIncremental = true
}

tasks.withType<Test> {
	useJUnitPlatform()
}
