import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_BOOT)
    id(Plugins.Id.ASCII_DOCTOR)

    kotlin(Plugins.Modules.SPRING)
    kotlin(Plugins.Modules.JPA)
}

dependencies {
    implementation(projects.goodgameDomain)
    implementation(projects.goodgameApplication)
    implementation(projects.infrastructure.log)
    implementation(projects.goodgamePersistance.rdb)

    implementation(Dependencies.Spring.Boot.WEB)

    testImplementation(Dependencies.Spring.RestDocs.MOCK_MVC)
    testImplementation(Dependencies.Spring.RestDocs.ASCII_DOCTOR)
}

tasks {
    val snippetsDir by extra { file("build/generated-snippets") }

    getByName<Jar>("jar") {
        enabled = false
    }

    getByName<BootJar>("bootJar") {
        enabled = true

        dependsOn(asciidoctor)
        from("build/docs/asciidoc")
        into("BOOT-INF/classes/static/docs")
    }

    build {
        dependsOn(getByName("copyDocs"))
    }

    register<Copy>("copyDocs") {
        dependsOn(asciidoctor)

        from("build/docs/asciidoc")
        into("src/main/resources/static/docs")
    }

    asciidoctor {
        inputs.dir(snippetsDir)
        dependsOn(test)
    }

    test {
        useJUnitPlatform()
        outputs.dir(snippetsDir)
    }

    clean {
        delete("src/main/resources/static/docs")
    }
}
