
For my other project.

# Use as a submodule

Run these commands from the root of your project:

```powershell
git submodule add https://github.com/lanlacope/android-rewheel.git libs/android-rewheel
git submodule update --init --recursive
git -C libs/android-rewheel sparse-checkout set --no-cone '/*' '!/example/'
Copy-Item .\local.properties .\libs\android-rewheel\local.properties
```

The submodule uses its own Gradle build and version catalog. Copying `local.properties` provides the Android SDK path to that build. The file is machine-specific and should not be committed.

## Include the build

Add this to the root `settings.gradle.kts`:

```kotlin
includeBuild("libs/android-rewheel")
```

## Add the dependency

Add this alias to the `[libraries]` section of your project's `gradle/libs.versions.toml`:

```toml
lanlacope-rewheel = { group = "io.github.lanlacope.android-rewheel", name = "army-knife" }
```

Then add the dependency to your app module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.lanlacope.rewheel)
}
```

## Update the submodule

```powershell
git submodule update --remote libs/android-rewheel
```

Commit the updated submodule reference in your project to share the update.