package com.mainproject.NotificationService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,code = HttpStatus.FORBIDDEN ,reason = "Username Already Taken")
public class UserAlreadyExist extends Exception{
}
