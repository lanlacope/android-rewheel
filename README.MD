for my project

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
                username = "lanlacope"
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