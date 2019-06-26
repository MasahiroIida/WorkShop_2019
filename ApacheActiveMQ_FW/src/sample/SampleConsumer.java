package sample;

import javax.jms.JMSException;

import messaging.ConsumeListener;
import messaging.MessagingAPI;

public class SampleConsumer {

	public static void main(String[] args) throws InterruptedException {
		MessagingAPI api = new MessagingAPI();
		try {
			api.initialize();
			api.startConsumer(new ConsumeListener(){});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
