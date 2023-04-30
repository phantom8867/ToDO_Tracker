package com.mainproject.NotificationService.Services;

import com.mainproject.NotificationService.Domain.UsersDTO;
import com.mainproject.NotificationService.Exception.UserAlreadyExist;
import com.mainproject.NotificationService.Exception.UserNotFound;

public interface UsersDtoServiceInterface {
//    UsersDTO addUser(UsersDTO usersDTO)throws UserAlreadyExist;
    public void welcomeMail(UsersDTO usersDTO);
//    public void settask(UsersDTO usersDTO);
    public void verification(UsersDTO usersDTO);

    public String otpSender(UsersDTO usersDTO);

    public void resetpassword(UsersDTO usersDTO);

}
