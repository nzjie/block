package com.block.active;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicComsumer {

	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://www.ajie18.top:61616");
		Connection connect = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			connect = factory.createConnection("admin", "admin");
			connect.start();
			session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("test-topic");
			consumer = session.createConsumer(topic);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					if (message instanceof TextMessage) {
						TextMessage mes = (TextMessage) message;
						System.out.println(mes);
					}

				}
			});
			// 防止线程执行完毕
			System.in.read();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connect)
					connect.close();
				if (null != session)
					session.close();
				if (null != consumer)
					consumer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}

	}

}
