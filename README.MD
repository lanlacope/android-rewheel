for my project

```
# local.properties
gpr.user=lanlacope
gpr.token=ghp_...
```

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        /* other repositories, example
         * google()
         * mavenCentral()
         */
        maven {
            url = uri("https://maven.pkg.github.com/lanlacope/android-rewheel")
            credentials {
                val localProperties = Properties()
                localProperties.load(file("${rootProject.projectDir}/local.properties").inputStream())
                username = localProperties.getProperty("gpr.user")
                password = localProperties.getProperty("gpr.token")
            }
        }
    }
}
```

```toml
# libs.versions.tomp
[versions]
lanlacope = "1.0.0"

[libraries]
lanlacope-rewheel-basic = { group = "io.github.lanlacope.android-rewheel", name = "basic", version.ref = "lanlacope"}
```

```kotlin
// build.gradle.kts
dependencies {
    releaseImplementation(libs.lanlacope.rewheel.basic)
}
```