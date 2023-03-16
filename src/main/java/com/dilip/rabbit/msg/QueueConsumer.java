package com.dilip.rabbit.msg;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.dilip.rabbit.msg.model.Person;

// This will consume the messages from RabbitMQ
// If this is not running for some reason, then all the 
// messages will be sitting in the queue
@Component
public class QueueConsumer {
	
	@RabbitListener(queues = {"${rabbitmq.simple.queue.name}"})
	public void receive(@Payload String body) {
		System.out.println("Messge:  "+ body);
	}
	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void receivePerson(@Payload Person person) {
		System.out.println("Messge:  "+ person);
	}
}
