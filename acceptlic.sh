(while sleep 3; do echo "y"; done) | %ANDROID_HOME%\tools\android update sdk --no-ui --all --filter "build-tools-25.0.1,android-25,extra-android-m2repository"
