# GOLD MONITOR

Android app project (Kotlin + Jetpack Compose), EUR display, gold overview, forecast and accuracy screens.

## Build APK
GitHub Actions builds a debug APK on every push to `main` and can be started manually:
1. Open the **Actions** tab.
2. Select **Build Android APK**.
3. Choose **Run workflow** if needed.
4. Download the `gold-monitor-debug-apk` artifact from the completed run.
5. Extract the ZIP and install `app-debug.apk` on Android.

## Current status
This first build is the Android UI shell. It intentionally does not display invented prices or forecasts. Live market data and forecast backend integration are separate work and are not yet connected.
