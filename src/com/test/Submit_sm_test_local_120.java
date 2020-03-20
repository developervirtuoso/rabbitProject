package com.test;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Submit_sm_test_local_120 {
	static Logger log = Logger.getLogger(Submit_sm_test_local_120.class.getName()); 
	public static void main(String[] args) {
		try {
		
		String QUEUE_NAME = "submit.sm.test_local_120";
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("jasmin");
		factory.setPassword("jasmin");
		com.rabbitmq.client.Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		try {
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			//byte[] byteArray = delivery.getBody();
			try
			{
				String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println(" [x] Received '" + message + "'");
	            log.info(" [x] Received '" + message + "'");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
		} catch (Exception e2) {
			e2.printStackTrace();
			 System.out.println("2222222"+e2.getMessage());
		}finally {
		}
		} catch (Exception e2) {
			e2.printStackTrace();
			 System.out.println("111111111111"+e2.getMessage());
		}
		System.out.println("Runnnnnnnnnn ");
	}
}
