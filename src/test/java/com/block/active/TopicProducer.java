package com.block.active;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 订阅模式
 * 
 * @author ajie
 *
 */
public class TopicProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://www.ajie18.top:61616");
		Connection connect = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			connect = factory.createConnection("admin", "admin");
			connect.start();
			session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("test-topic");
			producer = session.createProducer(topic);
			TextMessage message = session.createTextMessage("hello topic queuq");
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connect)
					connect.close();
				if (null != session)
					session.close();
				if (null != producer)
					producer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
