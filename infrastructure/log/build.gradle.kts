import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT)
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

dependencies {
    implementation(Dependencies.Spring.Boot.LOG4J2)
}
