import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Id.SPRING_BOOT) version Versions.SPRING_BOOT apply false
    id(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT) version Versions.SPRING_DEPENDENCY_MANAGEMENT
    id(Plugins.Id.KSP) version Versions.KSP
    id(Plugins.Id.KTLINT) version Versions.KTLINT
    id(Plugins.Id.KTLINT_IDEA) version Versions.KTLINT
    id(Plugins.Id.ASCII_DOCTOR) version Versions.ASCII_DOCTOR

    kotlin(Plugins.Modules.JVM) version Versions.KOTLIN
    kotlin(Plugins.Modules.SPRING) version Versions.KOTLIN apply false
    kotlin(Plugins.Modules.JPA) version Versions.KOTLIN apply false
    kotlin(Plugins.Modules.KAPT) version Versions.KOTLIN
}

buildscript {
    dependencies {
        classpath(kotlin(Plugins.Modules.GRADLE_PLUGIN, Versions.KOTLIN))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allprojects {
    apply {
        plugin<JavaLibraryPlugin>()
        plugin<KotlinPlatformJvmPlugin>()
    }

    repositories {
        mavenCentral()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

subprojects {
    dependencies {
        implementation(Dependencies.Kotlin.REFLECT)
        implementation(Dependencies.Kotlin.STDLIB_JDK8)

        testImplementation(Dependencies.Kotest.RUNNER_JUNIT5)
        testImplementation(Dependencies.Kotest.ASSERTION)
        testImplementation(Dependencies.Kotest.PROPERTY)

        testImplementation(Dependencies.Mockk.MOCKK)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

val springProjects = listOf(
    projects.goodgameApi,
    projects.goodgameSecurity,
    projects.goodgamePersistence.rdb,
    projects.infrastructure.log,
    projects.infrastructure.jwt
).map { it.dependencyProject }

configure(springProjects) {
    apply {
        plugin(Plugins.Id.SPRING_BOOT)
        plugin(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT)
    }

    dependencyManagement {
        imports {
            mavenBom(Dependencies.Management.SPRING_CLOUD)
            mavenBom(Dependencies.Management.AWSPRING_CLOUD)
        }
    }

    dependencies {
        implementation(Dependencies.Spring.Boot.VALIDATION)
        implementation(Dependencies.Spring.Boot.CONFIGURATION_PROCESSOR)
        annotationProcessor(Dependencies.Spring.Boot.CONFIGURATION_PROCESSOR)

        testImplementation(Dependencies.Spring.Boot.TEST) {
            exclude("org.junit.vintage", "junit-vintage-engine")
        }
        testImplementation(Dependencies.Kotest.SPRING_EXTENSION)
        testImplementation(Dependencies.Mockk.SPRING_MOCKK)

        testRuntimeOnly(Dependencies.Database.H2)
    }

    configurations {
        all {
            exclude(Plugins.Id.SPRING_BOOT, "spring-boot-starter-logging")
        }
    }
}
