cp base-truststore.ks client-trusstore.ks
echo "Use password: changit"
keytool -importcert -file mediaserver.cert -alias mediastore -keystore client-truststore.ks
