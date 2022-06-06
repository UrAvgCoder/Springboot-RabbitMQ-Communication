package com.example.rabbitmqdemo.producer;

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
import com.example.protobuf.*;

@RestController
public class NotificationProducer {

	@Autowired
	private RabbitTemplate template;

	@Value("${rabbitmq.props.exchange}")
	private String exchange;

	@Value("${rabbitmq.props.routingKey}")
	private String routingKey;

	@Value("${aes.encrypt.secretKey}")
	private String secretKey;

	@PostMapping(path = "/send")
	public String sendNotif(@RequestBody Notification notification) {
		byte[] data = ProtoBufDemo.Notification.newBuilder().setMessage(DataEncrypt.encrypt(notification.getMessage(), secretKey))
				.setStatus(DataEncrypt.encrypt(notification.getStatus(), secretKey)).build().toByteArray();
		template.convertAndSend(exchange, routingKey, data);
		return "Notification Sent!";
	}

	@GetMapping("/getMessage")
	public String getNotif() {
		return ProtoBufDemo.Notification.newBuilder().setMessage("Hello World!").toString()
				.concat(ProtoBufDemo.Notification.newBuilder().setStatus("Sent!").toString());
	}

}
