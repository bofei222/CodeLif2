package com.bf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bf.constants.RabbitConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bofei
 * @date 2018/7/12 9:52
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback, InitializingBean{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Integer i) {
//        String context = "hello " + i + " " + new Date();
        JSONObject jo = new JSONObject();
        jo.put("age", i);
        jo.put("name", "hello");
        System.out.println("Sender : " + JSON.toJSONString(jo));
        this.rabbitTemplate.convertAndSend(RabbitConstants.TRADE_EXCHANGE, RabbitConstants.TRADE_ROUTING_KEY, jo);
//        this.rabbitTemplate.convertAndSend(RabbitConstants.TRADE_QUEUE, jo);
    }

    public void newTask() {
        int[] arr = {10, 1, 9, 2, 8, 3};
        for (int i = 0; i < arr.length; i++) {
            String message = "bofei:" + arr[i];
            this.rabbitTemplate.convertAndSend(RabbitConstants.TRADE_QUEUE, message);
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack == true) {
            System.out.println("消息成功到达交换机，若return没有回调，则消息已持久化");
        } else {
            System.out.println("消息没有到交换机，重发消息。。。");
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息到了交换机，从交换机到不了队列");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}
