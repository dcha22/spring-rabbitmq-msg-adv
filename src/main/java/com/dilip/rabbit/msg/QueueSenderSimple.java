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
public class QueueSenderSimple {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	//@Autowired
	//private Queue queueSimple;

	/*
	 * @Autowired private DirectExchange exchange;
	 */

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.simple.routingkey}")
	private String routingkeySimple;

	public void sendSimple(String message) throws InterruptedException {
		//Thread.sleep(10000);
		//rabbitTemplate.convertAndSend(this.queue.getName(), order);
		rabbitTemplate.convertAndSend(exchange, routingkeySimple, message);
	}
}
