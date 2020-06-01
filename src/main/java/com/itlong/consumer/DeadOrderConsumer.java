package com.itlong.consumer;

import com.itlong.bean.OrderInfo;
import com.itlong.dto.SendMessage;
import com.itlong.mapper.OrderInfoMapper;
import com.itlong.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RabbitListener(queues = "dead_item_queue")
@Component
public class DeadOrderConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(DeadOrderConsumer.class);
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @RabbitHandler
    public void process(String orderId){
        LOGGER.info("死信队列收到过期订单ID------->dead_item_queue:"+orderId);
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderById(orderId);
        if(orderInfos!=null && !(orderInfos.get(0).getOrderStatus()==(byte)1)){
            orderInfoMapper.delItemByOrderId(orderId);
        }

    }

}
