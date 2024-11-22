for my project

```kotlin
// settings.gradle.kts
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/lanlacope/android-rewheel")
            credentials {
                username = "lanlacope"
                password = "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"
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