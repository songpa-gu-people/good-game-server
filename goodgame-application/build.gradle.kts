tasks.getByName<Jar>("jar") {
    enabled = true
}


dependencies {
    implementation(projects.goodgameDomain)
}
