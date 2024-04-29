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
        maven {
            setUrl("https://jitpack.io")
        }
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
include(":costumizelisneractivity")
include(":mvp_demo")
include(":h5")
include(":firebasedemo")
include(":costimizedialog")
include(":costumizeadpter")
include(":costumizelayout")
include(":shortcut")
include(":jetpackcompoment")
include(":jetpackpaging")
