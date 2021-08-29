package com.pp.spring.kafka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<String> messages = new ArrayList<>();
	User u=null;

	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg(){
		return messages;
	}

	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMsg(){
		return u;
	}

	@KafkaListener(groupId = "pp-consumer-1",topics = "kafka-test",containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMessage(String data){
		messages.add(data);
		return messages;
	}

	@KafkaListener(groupId = "pp-consumer-2",topics = "kafka-test",containerFactory = "userKafkaListenerContainerFactory")
	public User getJsonMessage(User user){
		u=user;
		return u;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
