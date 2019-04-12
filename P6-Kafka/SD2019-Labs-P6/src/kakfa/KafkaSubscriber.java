package kakfa;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaSubscriber {

	private static final long POLL_TIMEOUT = 1L;

	final KafkaConsumer<String, String> consumer;

	public KafkaSubscriber(List<String> topics) {
		this.consumer = getConsumer();
		this.consumer.subscribe(topics);
	}

	public void consume(SubscriberListener listener) {
		for (;;) {
			consumer.poll(Duration.ofSeconds(POLL_TIMEOUT)).forEach(r -> {
				listener.onReceive(r.topic(), r.key(), r.value());
			});
		}
	}

	static public KafkaConsumer<String, String> getConsumer() {

		Properties props = new Properties();

		// Localização dos servidores kafka (lista de máquinas + porto)
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, kafka:9092");

		// Configura o modo de subscrição (ver documentação em kafka.apache.org)
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		props.put(ConsumerConfig.GROUP_ID_CONFIG, "grp" + new java.util.Random(System.nanoTime()).nextLong());

		// Classe para serializar as chaves dos eventos (string)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// Classe para serializar os valores dos eventos (string)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// Cria um consumidor (assinante/subscriber)
		return new KafkaConsumer<String, String>(props);
	}

	public static interface SubscriberListener {
		void onReceive(String topic, String key, String value);
	}
}
