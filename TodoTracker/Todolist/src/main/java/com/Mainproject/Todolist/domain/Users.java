package com.Mainproject.Todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Users {
    @Id
    private String email ;
    private String firstname;
    private String password;
    private String phonenumber;
    private String profileimg;
    private String role;
    private String otp;
    private String verify;
    private String suggestion;
    private List<Userinfo>  friends;
    private List<Userinfo> Request;
    private List<Userinfo> Requested;
    private List<TodoList> todoList;
    private List<TodoList> requesttodolist;
    public Users(String email, String firstname, String password, String phonenumber, String profileimg, String role, String otp, String verify, String suggestion, List<TodoList> todoList,List<TodoList> requesttodolist) {
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.phonenumber = phonenumber;
        this.profileimg = profileimg;
        this.role = role;
        this.otp = otp;
        this.verify = verify;
        this.suggestion = suggestion;
        this.todoList = todoList;
        this.requesttodolist=requesttodolist;

    }
}
