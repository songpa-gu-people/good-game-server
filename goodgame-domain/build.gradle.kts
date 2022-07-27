tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies{
    testImplementation(Dependencies.Kotest.RUNNER_JUNIT5)
    testImplementation(Dependencies.Kotest.ASSERTION)
    testImplementation(Dependencies.Kotest.PROPERTY)
}
