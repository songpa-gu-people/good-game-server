import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_BOOT)

    kotlin(Plugins.Modules.SPRING)
    kotlin(Plugins.Modules.JPA)
    kotlin(Plugins.Modules.KAPT)

    // TestFixture
    `java-library`
    `java-test-fixtures`
    `maven-publish`
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
    implementation(projects.infrastructure.log)

    implementation("${Dependencies.Database.FLYWAY}:${Versions.FLY_WAY}")
    api(Dependencies.Spring.Boot.DATA_JPA)

    runtimeOnly(Dependencies.Database.H2)
    runtimeOnly(Dependencies.Database.MARIADB)

    api(Dependencies.Querydsl.JPA)

    kapt(Dependencies.Querydsl.APT)

    testFixturesImplementation(projects.infrastructure.log)
}
