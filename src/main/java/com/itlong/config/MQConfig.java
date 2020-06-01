package com.itlong.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {



    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("order_item_exchange",true,false);
    }

    @Bean
    public FanoutExchange FanoutExchange(){
        return new FanoutExchange("dead_item_direct_exchange",true,false);
    }

    @Bean
    Queue queue(){
        return new Queue("dead_item_queue",true);
    }


    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return  new RabbitAdmin(connectionFactory);
   }


       @Bean
       Binding binding(){
            return BindingBuilder.bind( queue()).to(FanoutExchange());
       }


}
