package com.example.rabbitmqdemo.producer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmqdemo.consumer.NotificationConsumer;
import com.example.rabbitmqdemo.entity.Notification;
import com.example.rabbitmqdemo.util.DataEncrypt;

@RestController
public class NotificationProducer {

	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private NotificationConsumer notifConsumer;

	@Value("${rabbitmq.props.exchange}")
	private String exchange;

	@Value("${rabbitmq.props.routingKey}")
	private String routingKey;

	@Value("${aes.encrypt.secretKey}")
	private String secretKey;

	@PostMapping(path = "/send")
	public String sendNotif(@RequestBody Notification notification) {
		notification.setMessage(DataEncrypt.encrypt(notification.getMessage(), secretKey));
		notification.setStatus(DataEncrypt.encrypt(notification.getStatus(), secretKey));
		template.convertAndSend(exchange, routingKey, notification);
		return "Notification Sent!";
	}

	@GetMapping("/getMessage")
	public String getNotif() {
		return notifConsumer.getList().toString(); 
	}

}
