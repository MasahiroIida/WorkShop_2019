package messaging;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessagingAPI {

	private Connection connection;

	private Session session;

	private MessageProducer producer;

	private MessageConsumer consumer;
	
	public MessagingAPI(){
		super();
	}

	public void initialize() throws JMSException {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public void startProducer() throws JMSException {
		// Create the destination (Topic or Queue)
		Destination destination = session.createTopic("API");

		// Create a MessageProducer from the Session to the Topic or Queue
		producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	public void stopProducer() {
		try {
			producer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			//
		}

	}

	public void send(String text) throws JMSException {
		TextMessage message = session.createTextMessage(text);
		System.out.println("Sent message: " + message.getText() + " : " + Thread.currentThread().getName());
		producer.send(message);
	}

	public void startConsumer(ConsumeListener listener) throws JMSException {
		MessagingConsumer messagingCounsumer = new MessagingConsumer(listener);
		connection.setExceptionListener(messagingCounsumer);
		// Create the destination (Topic or Queue)
		Destination destination = session.createTopic("API");
		// Create a MessageConsumer from the Session to the Topic or Queue
		consumer = session.createConsumer(destination);
		Thread brokerThread = new Thread(messagingCounsumer);
		brokerThread.setDaemon(false);
		brokerThread.start();
	}

	public void stopConsumer() {
		try {
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			//
		}
	}

	public class MessagingConsumer implements Runnable, ExceptionListener {

		ConsumeListener listener;

		public MessagingConsumer(ConsumeListener listener) {
			this.listener = listener;
		}

		public void run() {
			try {
				while (true) {
					// Wait for a message
					Message message = consumer.receive();

					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						String text = textMessage.getText();
						System.out.println("Received: " + text);
						listener.doAction(text);
					} else {
						System.out.println("Received: " + message);
					}
				}
			} catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}

		@Override
		public void onException(JMSException arg0) {
			System.out.println("JMS Exception occured.");
		}
	}
}
