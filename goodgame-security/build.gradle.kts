import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_BOOT)

    kotlin(Plugins.Modules.SPRING)
    kotlin(Plugins.Modules.KAPT)
}


tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}

dependencies {
    implementation(projects.goodgameDomain)
    implementation(projects.goodgameApplication)
    implementation(projects.goodgamePersistance.rdb)
    implementation(projects.infrastructure.log)
    implementation(projects.infrastructure.jwt)

    implementation(Dependencies.JWT.JWT_API)
    runtimeOnly(Dependencies.JWT.JWT_IMPL)
    runtimeOnly(Dependencies.JWT.JWT_JACKSON)

    implementation(Dependencies.Spring.Boot.WEB)
    implementation(Dependencies.Spring.Boot.OAUTH2)
    testImplementation(Dependencies.Spring.Boot.SECURITY_TEST)
}
