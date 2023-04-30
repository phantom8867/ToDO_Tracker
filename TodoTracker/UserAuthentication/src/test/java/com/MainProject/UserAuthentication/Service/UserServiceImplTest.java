package com.MainProject.UserAuthentication.Service;

import com.MainProject.UserAuthentication.domain.Users;
import com.MainProject.UserAuthentication.exception.UserAlreadyExistsException;
import com.MainProject.UserAuthentication.exception.UserNotFoundException;
import com.MainProject.UserAuthentication.repository.UserRepository;
import com.MainProject.UserAuthentication.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Users users1,users2;
    List<Users> userlist;

    @BeforeEach
    void setUp(){
        users1=new Users("prabu@gmail.com","prabu123","OK","user");
        users2=new Users("prabu@gmail.com","prabu1223","OK","user");
        userlist= Arrays.asList(users1,users2);
    }

    @Test
    public void  givenUsertoregisterationSaveduserSuccess()throws UserAlreadyExistsException{
        when(userRepository.findById(users1.getEmail())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(users1);
        assertEquals(users1,userService.addUser(users1));
        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findById(any());
    }

    @Test
    public void givenUsertoDeleteshouldDeleteSuccess()throws UserNotFoundException{
        when(userRepository.findById(users1.getEmail())).thenReturn(Optional.ofNullable(users1));
        String flag=userService.deleteUser(users1.getEmail());
        assertEquals("user deleted successfully",flag);
        verify(userRepository,times(1)).deleteById(any());
        verify(userRepository,times(1)).findById(any());
    }

//    @Test
//    public void givenUserUpdateSuccessfully()throws UserNotFoundException{
//        when(userRepository.save(users1)).thenReturn(users1);
//        System.out.println(userRepository.save(users1));
//        Users users=userService.updateUser(users2);
//        System.out.println(users);
//        assertEquals("prabu1223",users.getPassword());
////        verify(userRepository,times(1)).save(any());
//        verify(userRepository,times(1)).findById(any());
//    }


    @AfterEach
    void tearDown() {
        users1=null;
        users2 = null;
    }

}
