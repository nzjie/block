package com.block.active;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestComsumerDemo {

	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://www.ajie18.top:61616");
		Connection connect = null;
		MessageConsumer consumer = null;
		try {
			connect = factory.createConnection("admin", "admin123");
			Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("first-test-active-queue");
			consumer = session.createConsumer(queue);
/*			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					if (message instanceof TextMessage) {
						TextMessage mes = (TextMessage) message;
						System.out.println(mes);
					}

				}
			});*/
			Message receive = consumer.receive();
			if (receive instanceof TextMessage) {
				TextMessage mes = (TextMessage) receive;
				mes.acknowledge();
				System.out.println(mes.getText());
			}
			// 目的是为了不让程序执行完毕退出
			System.in.read();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != connect)
					connect.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
