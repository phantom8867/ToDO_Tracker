package com.Mainproject.Todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Content Id Not Found")
public class ContentNotFound extends Exception{
}
