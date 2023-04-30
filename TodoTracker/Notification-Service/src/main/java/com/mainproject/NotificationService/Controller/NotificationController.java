package com.mainproject.NotificationService.Controller;

import com.mainproject.NotificationService.Domain.UsersDTO;
import com.mainproject.NotificationService.Exception.UserNotFound;
import com.mainproject.NotificationService.Services.UsersDtoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo/v3/")
public class NotificationController {
    private ResponseEntity responseEntity;

    @Autowired
    private UsersDtoServiceInterface usersDtoServiceInterface;


    @GetMapping("notify/otp")
    public ResponseEntity<?> sendotp(@RequestBody UsersDTO user){

            responseEntity=new ResponseEntity<>(usersDtoServiceInterface.otpSender(user), HttpStatus.OK);


        return responseEntity;
    }
}
