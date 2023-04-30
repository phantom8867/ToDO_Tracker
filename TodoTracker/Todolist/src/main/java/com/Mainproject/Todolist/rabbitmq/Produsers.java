package com.Mainproject.Todolist.rabbitmq;

import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Users;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Produsers {
    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDtoToQueue(Users users){
        rabbitTemplate.convertAndSend(directExchange.getName(),"todo",users);
    }

    public void sendOtpToQueue(Users users){
        System.out.println("ecxhange");
        rabbitTemplate.convertAndSend(directExchange.getName(),"otp",users);
    }

    public void sendpasswordToQueue(Users users){
        rabbitTemplate.convertAndSend(directExchange.getName(),"password",users);
    }


//    public void sendTodoToQueue(TodoList todoList, Users users){
//        users.getTodoList().add(todoList);
//
//        rabbitTemplate.convertAndSend(directExchange.getName(), "alert", users);}

    public Users sendverifyToQueue(Users users){
        rabbitTemplate.convertAndSend(directExchange.getName(),"OK",users);
        return users;
    }
}
