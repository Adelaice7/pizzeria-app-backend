plugins {
	java
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.rmeunier"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
//	implementation("org.springframework.boot:spring-boot-starter-security")//
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.security:spring-security-crypto:6.0.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	compileOnly("org.projectlombok:lombok:1.18.26")

	testImplementation("junit:junit:4.13.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test:6.0.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
