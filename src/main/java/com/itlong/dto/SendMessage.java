package com.itlong.dto;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class SendMessage {
    private static final Logger LOGGER= LoggerFactory.getLogger(SendMessage.class);
    private RabbitTemplate rabbitTemplate;

    private RabbitAdmin rabbitAdmin;

    private DirectExchange directExchange;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Autowired
    public void setDirectExchange(DirectExchange directExchange) {
        this.directExchange = directExchange;
    }

    public void SendMessage(String userId, String orderId){
        Map<String,Object> map=new HashMap<>();
        map.put("x-message-ttl", 5*60*1000);
        map.put("x-dead-letter-exchange","dead_item_direct_exchange");
        Queue queue = new Queue(userId + "_item_queue", true,false,false,map);
        LOGGER.info("已成功创建队列------->:"+userId + "_item_queue");
        rabbitAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(userId);
        rabbitAdmin.declareBinding(binding);
        LOGGER.info("已将队列绑定到交换机------->:order_item_exchange");
        rabbitTemplate.convertAndSend("order_item_exchange",userId,orderId);
    }

    public void getMessage(String userId){
        String msg  =(String) rabbitTemplate.receiveAndConvert(userId + "_item_queue");
        LOGGER.info("从队列----"+userId+"_item_queue---->中取出消息:"+msg);
    }
}
