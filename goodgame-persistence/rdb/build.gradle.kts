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


val copyYml = tasks.register<Copy>("copyYml") {
    from("../../secret/persistence/persistence-jpa.yml")
    into("src/main/resources/")
}


tasks.processResources {
    dependsOn(copyYml)
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}


dependencies {
    implementation(projects.goodgameDomain)
    implementation(projects.goodgameApplication)
    implementation(projects.infrastructure.log)

    implementation("${Dependencies.Database.FLYWAY}:${Versions.FLY_WAY}")
    implementation(Dependencies.Jackson.DATATYPE_JSR310)
    implementation(Dependencies.Jackson.MODULE_KOTLIN)

    api(Dependencies.Spring.Boot.DATA_JPA)

    runtimeOnly(Dependencies.Database.H2)
    runtimeOnly(Dependencies.Database.MARIADB)

    api(Dependencies.Querydsl.JPA)

    kapt(Dependencies.Querydsl.APT)

    testFixturesImplementation(projects.infrastructure.log)
}
