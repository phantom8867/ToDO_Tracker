package com.Mainproject.Todolist.TestForRepository.Services;

import com.Mainproject.Todolist.Exception.ContentNotFound;
import com.Mainproject.Todolist.Exception.UserAlreadyExist;
import com.Mainproject.Todolist.Exception.UserNotFound;
import com.Mainproject.Todolist.Respository.TodoRespository;
import com.Mainproject.Todolist.Service.TodoServiceImpl;
import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {
//    @Mock
//    private TodoRespository todoRespository;
//
//    @InjectMocks
//    private TodoServiceImpl todoServiceImpl;
//
//    private TodoList todoList;
//
//    private Users users1,users2;
//    List<TodoList> userlist;
//    List<Users> userlists;
//
//    @BeforeEach
//    void setUp(){
//        userlist=Arrays.asList(todoList);
//        todoList=new TodoList(1,"ddfs","jaja","23-12-2023","9:03","aws","wow","high");
//        users1=new Users("vinayaksolanki1923@gmail.com","vinayak","solanki","@Vinayak1233","@Vinayak1233","8630496822","","user","",userlist);
//        users2=new Users("vinayaksolanki1923@gmail.com","vinayakk","solankii","@Vinayak12333","@Vinayak12333","8630496822","","user","",userlist);
//
//    }
//
//    @Test
//    public void  givenUsertoregisterationSaveduserSuccess()throws UserAlreadyExist {
//        when(todoRespository.findById(users1.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(todoRespository.save(any())).thenReturn(users1);
//        assertEquals(users1,todoServiceImpl.registerUser(users1));
//        verify(todoRespository,times(1)).save(any());
//        verify(todoRespository,times(1)).findById(any());
//    }
//
//    @Test
//    public void givenCustomerToSaveReturnCustomerFailure(){
//        when(todoRespository.findById(users1.getEmail())).thenReturn(Optional.ofNullable(users1));
//        assertThrows(UserAlreadyExist.class,()->todoServiceImpl.registerUser(users1));
//        verify(todoRespository,times(0)).save(any());
//        verify(todoRespository,times(1)).findById(any());
//    }
//
//
//
//    @Test
//    public void givenUsertoDeleteshouldDeleteSuccess() throws UserNotFound, ContentNotFound {
//        when(todoRespository.findById(users1.getEmail())).thenReturn(Optional.ofNullable(users1));
//        Boolean flag=todoServiceImpl.deleteList(648464,"vinayaksolanki1923@gmail.com");
//        assertEquals("user deleted successfully",flag);
//        verify(todoRespository,times(1)).deleteById(any());
//        verify(todoRespository,times(1)).findById(any());
//    }
//
//
//    @AfterEach
//    void tearDown() {
//        users1=null;
//        users2 = null;
//    }

}
