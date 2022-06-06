package com.example.rabbitmqdemo.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.rabbitmqdemo.entity.Notification;
import com.example.rabbitmqdemo.util.DataEncrypt;

@Component
public class NotificationConsumer {

	private List<Notification> list = new ArrayList<>();

	@Value("${aes.encrypt.secretKey}")
	private String secretKey;

	public List<Notification> getList() {
		return list;
	}

	@RabbitListener(queues = "${rabbitmq.props.queue}")
	public void readMessageInQueue(Notification notification) {
		list.add(new Notification(DataEncrypt.decrypt(notification.getMessage(), secretKey),
				DataEncrypt.decrypt(notification.getStatus(), secretKey)));
	}
}
