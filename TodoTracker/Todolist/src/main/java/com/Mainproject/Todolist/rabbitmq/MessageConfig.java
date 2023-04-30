package com.Mainproject.Todolist.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class MessageConfig {
    private String exchange_name="ex_name";

    private String exchange_verify="ex_verify";
    private String queue_message= "q_message";
    private String queue_otp= "q_otp";
    private String queue_message2="verify";
    private String queue_reset="q_password";


    @Bean
    public DirectExchange getDirectExchange(){
        return  new DirectExchange(exchange_name);
    }


    @Bean
    public Queue getQueue(){
        return new Queue(queue_message);
    }
    @Bean
    public Queue getQueueforreset(){
        return new Queue(queue_reset);
    }

    @Bean
    public Queue getQueueforverify(){
        return new Queue(queue_message2);
    }

    @Bean
    public Queue getQueueforOtp(){
        System.out.println("queue");
        return new Queue(queue_otp);
    }

    @Bean
    public Jackson2JsonMessageConverter getJacksonConv(){return new Jackson2JsonMessageConverter();}
    @Bean
    public RabbitTemplate getRabbitTemp(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getJacksonConv());
        return rabbitTemplate;
    }
    @Bean
    public Binding getBinding( DirectExchange directExchange){
        return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("todo");
    }
    @Bean
    public Binding getBindingforOtp( DirectExchange directExchange){
        System.out.println("binder");
        return BindingBuilder.bind(getQueueforOtp()).to(getDirectExchange()).with("otp");
    }

    @Bean
    public Binding getBindingsetpassword( DirectExchange directExchange){
        return BindingBuilder.bind(getQueueforreset()).to(getDirectExchange()).with("password");
    }



    @Bean
    public Binding getBindingforverify(DirectExchange directExchange){
        return BindingBuilder.bind(getQueueforverify()).to(directExchange).with("OK");

    }

}
