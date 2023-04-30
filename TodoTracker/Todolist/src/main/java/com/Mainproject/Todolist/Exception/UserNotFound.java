package com.Mainproject.Todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User Not Found OR Incorrect Password/Username")
public class UserNotFound extends Exception{
}
