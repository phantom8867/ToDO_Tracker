package com.mainproject.NotificationService.Domain;

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
public class UsersDTO {
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
    private List<UserInfoSDTO>  friends;
    private List<UserInfoSDTO> Request;
    private List<UserInfoSDTO> Requested;
    private List<ToDo_DTO> todoList;
    private List<ToDo_DTO> requesttodolist;
}
