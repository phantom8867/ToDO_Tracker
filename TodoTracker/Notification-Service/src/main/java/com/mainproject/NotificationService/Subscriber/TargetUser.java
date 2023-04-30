package com.mainproject.NotificationService.Subscriber;

import com.mainproject.NotificationService.Domain.UsersDTO;
import com.mainproject.NotificationService.Exception.UserAlreadyExist;
import com.mainproject.NotificationService.Exception.UserNotFound;
import com.mainproject.NotificationService.Services.UsersDtoServiceInterface;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TargetUser {

    @Autowired
    private UsersDtoServiceInterface usersDtoServiceInterface;

    @RabbitListener(queues = "q_message")
    public void getDtoFromQueueAndAdd(UsersDTO usersDTO){
        try {

            usersDtoServiceInterface.welcomeMail(usersDTO);
        }catch(Exception e){
            System.out.println(e);
        }
    }

//    @RabbitListener(queues = "q_message1")
//    public void getDtoFromQueueAndAlert(UsersDTO usersDTO)throws UserAlreadyExist{
//
//        usersDtoServiceInterface.settask(usersDTO);
//    }

    @RabbitListener(queues = "q_otp")
    public void sendotp(UsersDTO usersDTO)throws UserNotFound{

        try{
            System.out.println("function");
            usersDtoServiceInterface.otpSender(usersDTO);
        }catch (Exception e){
            throw new UserNotFound();
        }
    }

    @RabbitListener(queues = "q_password")
    public void resetpassword(UsersDTO usersDTO)throws UserNotFound{
        try{
            UsersDTO usersDTO1=usersDTO;
            usersDtoServiceInterface.resetpassword(usersDTO1);
        }catch (Exception e){
            throw new UserNotFound();
        }
    }


    @RabbitListener(queues = "verify")
    public void getfromqueueandverify(UsersDTO usersDTO)throws UserNotFound{
        try {
            usersDtoServiceInterface.verification(usersDTO);
        }catch(Exception e){
            throw new UserNotFound();

        }
    }


}
