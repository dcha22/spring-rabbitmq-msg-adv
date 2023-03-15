package com.dilip.rabbit.msg;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dilip.rabbit.msg.model.Person;

@Component
public class QueueSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	/*
	 * @Autowired private DirectExchange exchange;
	 */

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routingkey}")
	private String routingkey;

	public void sendPerson(Person person) throws InterruptedException {
		//Thread.sleep(10000);
		//rabbitTemplate.convertAndSend(this.queue.getName(), order);
		rabbitTemplate.convertAndSend(exchange, routingkey, person);
	}

	public void send(String msg) throws InterruptedException {
		//rabbitTemplate.convertAndSend(this.queue.getName(), msg); 
		rabbitTemplate.convertAndSend(this.queue.getName(), msg);
	}

}
