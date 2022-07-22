import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT)

    kotlin(Plugins.Modules.SPRING)
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}


dependencies {
    implementation(projects.goodgameDomain)
    implementation(projects.goodgameApplication)

    implementation(Dependencies.JWT.JWT_API)
    runtimeOnly(Dependencies.JWT.JWT_IMPL)
    runtimeOnly(Dependencies.JWT.JWT_JACKSON)
}
