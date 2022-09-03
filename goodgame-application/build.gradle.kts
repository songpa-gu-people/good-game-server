tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(projects.goodgameDomain)

    api(Dependencies.Spring.Boot.DATA_JPA)

    testImplementation(Dependencies.Kotest.RUNNER_JUNIT5)
    testImplementation(Dependencies.Kotest.ASSERTION)
    testImplementation(Dependencies.Kotest.PROPERTY)
}
