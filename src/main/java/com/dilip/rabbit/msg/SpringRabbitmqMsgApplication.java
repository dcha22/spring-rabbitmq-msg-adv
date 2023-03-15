package com.dilip.rabbit.msg;


//Ref:  https://medium.com/javarevisited/first-steps-with-rabbitmq-and-spring-boot-81d293554703

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableRabbit // seems like this is not needed
@SpringBootApplication
public class SpringRabbitmqMsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqMsgApplication.class, args);
	}

}
