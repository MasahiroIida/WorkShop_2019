package sample;

import javax.jms.JMSException;

import messaging.MessagingAPI;

public class SampleProducer {
	public static void main(String[] args) throws InterruptedException {
		MessagingAPI api = new MessagingAPI();
		try {
			api.initialize();
			api.startProducer();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		//
		int count = 1;
		while (true) {
			try {
				api.send("Hello World. count:" + Integer.toString(count++));
			} catch (JMSException e) {
				e.printStackTrace();
			}
			//
			Thread.sleep(10000);
		}
	}
}
