package com.bf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bf.constants.RabbitConstants;
import com.bf.mapper.UserMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author bofei
 * @date 2018/7/12 10:27
 */
@Component
@RabbitListener(queues = RabbitConstants.TRADE_QUEUE)
public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(Receiver.class);
    @Resource
    private UserMapper userMapper;

    //    @RabbitHandler
    public void process2(Channel channel, JSONObject jo, Message message) throws Exception{
        System.out.println("Receiver : " +   JSON.toJSONString(jo));
        try {
//            userMapper.insert(jo.getString("name"), jo.getInteger("age"));
            String s = null;
            System.out.println("处理消息" + s.toString());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("插入错误{}",e);
//            throw new AmqpRejectAndDontRequeueException("插入错误" ,e);
//            logger.error("处理消息异常,消息体{}", JSON.toJSONString(jo), e);
        }
    }

    @RabbitHandler
    public void process(Channel channel, String string, Message message) throws Exception{
        System.out.println("Receiver : " +   string);
        try {
            doWork(string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    private static void doWork(String task) throws InterruptedException {
        String[] taskArr = task.split(":");
        TimeUnit.SECONDS.sleep(Long.valueOf(taskArr[1]));
    }
}
