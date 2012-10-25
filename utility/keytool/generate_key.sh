#keytool -genkey -v -keyalg RSA -keystore ~/.android/idumo.keystore -alias idumo -validity 10000
keytool -genkey -v -keyalg RSA -keystore ~/.android/debug.keystore -alias androiddebugkey -validity 10000 -dname "CN=Android Debug,O=Android,C=US"
