package com.block.active;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activemq生产者
 * 
 * @author ajie
 *
 */
public class TestProductDemo {

	public static void main(String[] args) {
		// 工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://www.ajie18.top:61616");
		Connection connect = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			connect = factory.createConnection();
			connect.start();
			session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("first-test-active-queue");
			producer = session.createProducer(queue);
			TextMessage message = session.createTextMessage("hello active");
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connect) {
					connect.close();
				}
				if (null != session) {
					session.close();
				}
				if (null != producer) {
					producer.close();
				}
			} catch (JMSException e2) {
				// TODO: handle exception
			}
		}
	}
}
