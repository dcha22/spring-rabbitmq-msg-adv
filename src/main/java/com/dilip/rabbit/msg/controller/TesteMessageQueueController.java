package com.dilip.rabbit.msg.controller;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dilip.rabbit.msg.QueueSender;
import com.dilip.rabbit.msg.QueueSenderSimple;
import com.dilip.rabbit.msg.model.Person;

@RestController
//@RequestMapping("/teste-DCH")
public class TesteMessageQueueController {
	
	@Autowired
	private QueueSender queueSender;
	
	@Autowired 
	private QueueSenderSimple queueSenderSimple;
	
	@GetMapping("/rabbitmsg")
	public String send() throws InterruptedException {
		queueSenderSimple.sendSimple("This is test message for Simple Message Queue");
		return "ok.finished";
	}
	
	@PostMapping("/pproducer")
	public ResponseEntity<String> send(@RequestBody Person person) throws InterruptedException {
		Person _person = new Person();
		
		_person.setId(person.getId());
		_person.setFirstName(person.getFirstName());
		_person.setLastName(person.getLastName());
		queueSender.sendPerson(_person);
		return new ResponseEntity<>("ok.posted", HttpStatus.OK);
	}
	
//	@PostMapping("/rabbitperson")
//	public String sendPerson(@RequestBody Person person) throws InterruptedException {
//		queueSender.sendPerson(person);
//		//Thread.sleep(10000);
//		return "ok.posted";
//	}
}
