rootProject.name = "goodgame"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "goodgame-api",
    "goodgame-domain",
    "goodgame-application",
    "goodgame-persistance:rdb",
    "goodgame-security",
    "infrastructure:log",
    "infrastructure:jwt",
)
