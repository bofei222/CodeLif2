package com.bf.config;

import com.bf.constants.RabbitConstants;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author bofei
 * @date 2018/7/12 9:55
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("compute-feign");
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        //声明死信队列（Fanout类型的exchange）
        Queue deadQueue = new Queue(RabbitConstants.DEAD_QUEUE);
        // 死信交换机
        FanoutExchange deadExchange = new FanoutExchange(RabbitConstants.DEAD_EXCHANGE);
        rabbitAdmin.declareQueue(deadQueue);
        rabbitAdmin.declareExchange(deadExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(deadQueue).to(deadExchange));

        // 业务交换机
        DirectExchange tradeExchange = new DirectExchange(RabbitConstants.TRADE_EXCHANGE);

        //声明业务队列（Direct类型的exchange）
        Queue tradeQueue = queue(RabbitConstants.TRADE_QUEUE);
        rabbitAdmin.declareQueue(tradeQueue);
        rabbitAdmin.declareExchange(tradeExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(tradeQueue).to(tradeExchange).with(RabbitConstants.TRADE_ROUTING_KEY));

        return rabbitAdmin;
    }

    public Queue queue(String name) {
        Map<String, Object> args = new HashMap<>();
        // 设置死信队列
        args.put("x-dead-letter-exchange", RabbitConstants.DEAD_EXCHANGE);
        args.put("x-dead-letter-routing-key", RabbitConstants.DEAD_ROUTING_KEY);
        // 设置消息的过期时间， 单位是毫秒
//        args.put("x-message-ttl", 5000);

        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(name, durable, exclusive, autoDelete, args);
    }

}
