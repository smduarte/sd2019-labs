package example;

import java.util.Arrays;
import java.util.List;

import kakfa.KafkaSubscriber;
import microgram.impl.srv.rest.JavaMedia;

public class MediaStorageEventSubscriber {

	public static void main(String[] args) throws Exception {

		List<String> topics = Arrays.asList(JavaMedia.MEDIA_STORAGE_EVENTS);

		KafkaSubscriber subscriber = new KafkaSubscriber(topics);

		subscriber.consume((topic, key, value) -> {
			System.out.printf("Kafka: topic: %s key: %s value: %s\n", topic, key, value);
		});
	}
}
