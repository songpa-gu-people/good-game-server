object Dependencies {
    object Management {
        const val AWSPRING_CLOUD = "io.awspring.cloud:spring-cloud-aws-dependencies:${Versions.AWSPING_CLOUD_DEPENDENCY_MANAGEMENT}"
        const val SPRING_CLOUD = "org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD_DEPENDENCY_MANAGEMENT}"
    }

    object Spring {
        object Boot {
            const val WEB = "org.springframework.boot:spring-boot-starter-web"
            const val DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
            const val LOG4J2 = "org.springframework.boot:spring-boot-starter-log4j2"
            const val BATCH = "org.springframework.boot:spring-boot-starter-batch"
            const val VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
            const val CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
            const val ACTUATOR = "org.springframework.boot:spring-boot-starter-actuator"
            const val TEST = "org.springframework.boot:spring-boot-starter-test"
            const val OAUTH2 = "org.springframework.boot:spring-boot-starter-oauth2-client"
            const val SECURITY_TEST = "org.springframework.security:spring-security-test"
        }

        object Batch {
            const val TEST = "org.springframework.batch:spring-batch-test"
        }

        object RestDocs {
            const val MOCK_MVC = "org.springframework.restdocs:spring-restdocs-mockmvc"
            const val ASCII_DOCTOR = "org.springframework.restdocs:spring-restdocs-asciidoctor"
        }

        object Cloud {
            const val CONFIG = "org.springframework.cloud:spring-cloud-starter-config"
            const val CLIENT = "org.springframework.cloud:spring-cloud-config-client"
            const val OPEN_FEIGN = "org.springframework.cloud:spring-cloud-starter-openfeign"
        }

    }

    object JWT {
        const val JWT_API = "io.jsonwebtoken:jjwt-api:${Versions.JWT}"
        const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${Versions.JWT}"
        const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${Versions.JWT}"
    }

    object Kotlin {
        const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
        const val STDLIB_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }

    object Kotest {
        const val RUNNER_JUNIT5 = "io.kotest:kotest-runner-junit5:${Versions.KOTEST}"
        const val ASSERTION = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
        const val PROPERTY = "io.kotest:kotest-property:${Versions.KOTEST}"

        const val SPRING_EXTENSION = "io.kotest.extensions:kotest-extensions-spring:${Versions.KOTEST_EXTENSION}"
        const val TEST_CONTAINER_EXTENSION = "io.kotest.extensions:kotest-extensions-testcontainers:${Versions.KOTEST_EXTENSION}"
    }

    object Mockk {
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val SPRING_MOCKK = "com.ninja-squad:springmockk:3.1.1"
    }

    object Database {
        const val H2 = "com.h2database:h2"
        const val MARIADB = "org.mariadb.jdbc:mariadb-java-client"
        const val FLYWAY = "org.flywaydb:flyway-core"
    }

    object Querydsl {
        const val JPA = "com.querydsl:querydsl-jpa:${Versions.QUERYDSL}"
        const val APT = "com.querydsl:querydsl-apt:${Versions.QUERYDSL}:jpa"
    }

    object Awspring {
        const val STARTER = "io.awspring.cloud:spring-cloud-starter-aws"
        const val MESSAGING = "io.awspring.cloud:spring-cloud-starter-aws-messaging"
    }

    object TestContainers {
        const val TESTCONTAINERS = "org.testcontainers:testcontainers:${Versions.TEST_CONTAINERS}"
        const val JUNIT_JUPITER = "org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINERS}"
        const val MARIA_DB = "org.testcontainers:mariadb:${Versions.TEST_CONTAINERS}"
        const val LOCALSTACK = "org.testcontainers:localstack:${Versions.TEST_CONTAINERS}"
    }

    object Jackson {
        const val MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}"
        const val DATATYPE_JSR310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.JACKSON}"
    }

    const val KSP = "com.google.devtools.ksp:symbol-processing-api:${Versions.KSP}"
    const val SFTP_SSHJ = "com.hierynomus:sshj:0.33.0"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:4.10.0"
    const val FEIGN_OK_HTTP = "io.github.openfeign:feign-okhttp"
}
