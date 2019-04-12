package kakfa;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

public class KafkaUtils {

	public static void createTopics(List<String> topics) {
		topics.forEach(topic -> createTopic(topic));
	}

	public static void createTopic(String topic) {
		createTopic(topic, 1, 1);
	}

	public static void createTopic(String topic, int numPartitions, int replicationFactor) {

		try (AdminClient client = create()) {

			client.createTopics(Arrays.asList(new NewTopic(topic, numPartitions, (short) replicationFactor))).all().get();

		} catch (ExecutionException x) {
			System.err.printf("Topic: %s already exists...\n", topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static private AdminClient create() {
		Properties props = new Properties();
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "5000");
		return AdminClient.create(props);
	}
}
