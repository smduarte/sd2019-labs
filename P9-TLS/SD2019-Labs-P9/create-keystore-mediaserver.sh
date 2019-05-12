keytool -genkey -alias server -keyalg RSA -validity 365 -keystore mediaserver.ks -storetype pkcs12
keytool -exportcert -alias server -keystore mediaserver.ks -file mediaserver.cert
