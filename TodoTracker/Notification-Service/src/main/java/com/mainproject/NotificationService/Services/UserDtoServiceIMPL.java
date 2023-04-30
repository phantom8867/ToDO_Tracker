package com.mainproject.NotificationService.Services;

import com.mainproject.NotificationService.Domain.ToDo_DTO;
import com.mainproject.NotificationService.Domain.UsersDTO;
import com.mainproject.NotificationService.Exception.UserAlreadyExist;
import com.mainproject.NotificationService.Exception.UserNotFound;
import com.mainproject.NotificationService.Repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class UserDtoServiceIMPL implements UsersDtoServiceInterface {

    @Autowired
    private UserRepos userRepos;


//    @Override
//    public UsersDTO addUser(UsersDTO usersDTO) {
////        if(userRepos.findById(usersDTO.getEmail()).isPresent()){
////            ret
////        }else {
//            return userRepos.save(usersDTO);
////        }
//
//    }

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void welcomeMail(UsersDTO usersDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("todotracker01@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getFirstname()+"\nWelcome to Todo Tracker\nCongratulations Your Account has been Created");
        message.setSubject("Todo Tracker Welcome");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void verification(UsersDTO usersDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("todotracker01@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getFirstname()+"\nWelcome to Todo Tracker\nTo Verify Your email Click Here http://localhost:4200/verified/"+usersDTO.getEmail());
        message.setSubject("Todo Tracker Verification");

        mailSender.send(message);
        System.out.println("Mail Send...");

    }

    @Override
    public String otpSender(UsersDTO usersDTO) {
        System.out.println("sending email");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("todotracker01@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getFirstname()+"\nYour One time password is : "+usersDTO.getOtp());
        message.setSubject("Todo Tracker OTP");

        mailSender.send(message);
        System.out.println("Mail Send...");
        return "Mail Send...";

    }

    @Override
    public void resetpassword(UsersDTO usersDTO) {

        System.out.println("sending email");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("todotracker01@gmail.com");
        message.setTo(usersDTO.getEmail());
        message.setText("Hello "+usersDTO.getFirstname()+"\nYour Password reset is DONE\nYour Email is  : "+usersDTO.getEmail()+"\nYour Password is : "+usersDTO.getPassword());
        message.setSubject("Todo Tracker Password reset");
        mailSender.send(message);
        System.out.println("Mail Send...");

    }


//    public void settask(UsersDTO usersDTO){
////        ToDo_DTO toDo_dto= usersDTO.getToDo_dtoList();
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("todotracker01@gmail.com");
//        message.setTo(usersDTO.getEmail());
//        message.setText("Dear "+usersDTO.getFirstname()+",\nYou have added new task\nTitle : ");
//        message.setSubject("New Task is Added");
//    }


}
