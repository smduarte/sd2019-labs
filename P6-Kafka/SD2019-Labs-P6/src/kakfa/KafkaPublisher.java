package kakfa;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaPublisher {

	final KafkaProducer<String, String> producer;

	public KafkaPublisher() {
		this.producer = getProducer();
	}

	static public KafkaProducer<String, String> getProducer() {

		Properties props = new Properties();

		// Localização dos servidores kafka (lista de máquinas + porto)
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, kafka:9092");

		// Classe para serializar as chaves dos eventos (string)
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// Classe para serializar os valores dos eventos (string)
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		return new KafkaProducer<String, String>(props);
	}

	public void close() {
		this.producer.close();
	}

	public void publish(String topic, String key, String value) {
		try {
			producer.send(new ProducerRecord<String, String>(topic, key, value)).get();

		} catch (ExecutionException | InterruptedException x) {
			x.printStackTrace();
		}
	}
}
