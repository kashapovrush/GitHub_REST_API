pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GitHub REST API"
include(":app")
include(":core")
include(":features")
include(":features:search-repository-screen")
include(":features:repository-user")
include(":features:about-programm")
include(":core:api")
include(":core:util")
include(":core:navigation")
include(":features:common")
