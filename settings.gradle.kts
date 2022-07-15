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
    "goodgame-persistance",
    "goodgame-persistance:rdb",
    "infrastructure",
    "infrastructure:log"
)
include("goodgame-security")
