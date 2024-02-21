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

rootProject.name = "Retrofit_RxJava"
include(":app")
include(":recycleview")
include(":launchapp")
include(":costumizeviewactivity")
include(":service")
include(":broadcast")
include(":eventbus")
include(":evenbus")
include(":handler")