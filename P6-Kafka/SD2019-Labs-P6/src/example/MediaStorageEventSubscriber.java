package example;

import java.util.Arrays;

import kakfa.KafkaSubscriber;
import microgram.impl.srv.rest.JavaMedia;

public class MediaStorageEventSubscriber {

	public static void main(String[] args) throws Exception {

		KafkaSubscriber sub = new KafkaSubscriber(Arrays.asList(JavaMedia.MEDIA_STORAGE_EVENTS), (topic, key, value) -> {
			System.out.printf("Kafka: topic: %s key: %s value: %s\n", topic, key, value);
		});

		sub.start();
	}
}
