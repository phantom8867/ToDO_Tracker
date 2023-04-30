package com.MainProject.UserAuthentication.service;

import com.MainProject.UserAuthentication.domain.Users;
import com.MainProject.UserAuthentication.exception.UserAlreadyExistsException;
import com.MainProject.UserAuthentication.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public Users addUser(Users users) throws UserAlreadyExistsException;
    public List<Users> getAllUser();
    public String deleteUser(String email) throws UserNotFoundException;
    public Users updateUser(Users user)throws UserNotFoundException;
    public Users loginCheck(String email, String password);
}
