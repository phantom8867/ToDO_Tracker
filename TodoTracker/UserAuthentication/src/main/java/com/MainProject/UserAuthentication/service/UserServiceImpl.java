package com.MainProject.UserAuthentication.service;

import com.MainProject.UserAuthentication.domain.Users;
import com.MainProject.UserAuthentication.exception.UserAlreadyExistsException;
import com.MainProject.UserAuthentication.exception.UserNotFoundException;
import com.MainProject.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Users addUser(Users users) throws UserAlreadyExistsException {
        if(userRepository.findById(users.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(users);
    }

    @Override
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(email);
        return "user deleted successfully";
    }

    @Override
    public Users updateUser(Users user) throws UserNotFoundException {
        //1. check user is present

        System.out.println(user.getEmail());
        System.out.println(userRepository.findById(user.getEmail()).isPresent());
        if(userRepository.findById(user.getEmail()).isPresent()) {
            //2. fetch record
            System.out.println(user.getEmail());
            Users users1 = userRepository.findById(user.getEmail()).get();
            System.out.println(users1);
            if (users1.getEmail().equals(user.getEmail())) {
                //3. update record ----> setter
                if (users1.getPassword() != null) {
                    users1.setPassword(user.getPassword());
                }
                if (users1.getVerify() != null) {
                    users1.setVerify(user.getVerify());
                }
//                if (users1.getRole() != null) {
//                    users1.setRole(user.getRole());
//                }

            }
            System.out.println(users1);
            System.out.println(user);
            //4. store record by using save(user) and return
            return userRepository.save(users1);
        }else throw new UserNotFoundException();

    }

    @Override
    public Users loginCheck(String email, String password) {
        if(userRepository.findById(email).isPresent()) {
            //fetch user object by using email
            Users user = userRepository.findById(email).get();
            //check password
            if(user.getVerify().equals("OK")){
            if(user.getPassword().equals(password)) {
                //valid user
                return user;
            }
            else {
                //invalid user
                return null;
            }
            }else{
                return null;
            }
        }
        else {
            //if user does not exist
            return null;
        }
    }
}
