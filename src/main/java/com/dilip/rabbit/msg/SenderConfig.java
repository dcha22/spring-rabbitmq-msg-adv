package com.dilip.rabbit.msg;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {
	// queue for json objects
	@Value("${rabbitmq.queue.name}")
	private String queueName;
	
	// queue for simple string messages
	@Value("${rabbitmq.simple.queue.name}")
	private String queueNameSimple;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	// for Json Messages
	@Value("${rabbitmq.routingkey}")
	private String routingkey;
	
	// For simple text messages
	@Value("${rabbitmq.simple.routingkey}")
	private String routingkeySimple;
	
	
	@Bean
	public Queue queue() {
		return new Queue(this.queueName, true);
	}

	@Bean
	public Queue queueSimple() {
		return new Queue(this.queueNameSimple, true);
	}
	
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	/*
	 * This bean will bind the message in exchange to a queue.
	 */
	@Bean 
	Binding binding(Queue queue, DirectExchange exchange) { 
		return BindingBuilder.bind(queue).to(exchange).with(routingkey); 
	}
	
	/*
	 * This bean will bind the simple text message in exchange to a queue.
	 */
	@Bean 
	Binding bindingSimple(Queue queueSimple, DirectExchange exchange) { 
		return BindingBuilder.bind(queueSimple).to(exchange).with(routingkeySimple); 
	}

	/*
	 * This is a converter bean to turn the payload of a message from serialized form
	 * to a typed object and vice versa.
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
