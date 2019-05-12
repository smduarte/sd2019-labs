cp base-truststore.ks client-truststore.ks
echo "Use password: changeit"
keytool -importcert -file mediaserver.cert -alias mediastore -keystore client-truststore.ks
