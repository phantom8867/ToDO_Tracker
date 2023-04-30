package com.Mainproject.Todolist.TestForRepository.Controller;
import com.Mainproject.Todolist.Controller.TodoController;
import com.Mainproject.Todolist.Exception.ContentNotFound;
import com.Mainproject.Todolist.Exception.UserNotFound;
import com.Mainproject.Todolist.Service.TodoServiceImpl;
import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoServiceImpl todoService;

    @InjectMocks
    private TodoController todoController;

    private Users users1,users2;
    private TodoList todoList;
    List<TodoList> userlist;

//    @BeforeEach
//    void setUp(){
//        userlist=Arrays.asList(todoList);
//        todoList=new TodoList(2,"ddfs","jaja","23-12-2023","9:03","aws","wow","high");
//        users1=new Users("vinayaksolanki1923@gmail.com","vinayak","solanki","@Vinayak1233","@Vinayak1233","8630496822","","user","",userlist);
//        users2=new Users("vinayaksolanki1923@gmail.com","vinayakk","solankii","@Vinayak12333","@Vinayak12333","8630496822","","user","",userlist);
//        mockMvc= MockMvcBuilders.standaloneSetup(todoController).build();
//    }

//    @Test
//    public void givenUsertoregistration() throws Exception {
//        when(todoService.registerUser(any())).thenReturn(users1);
//        mockMvc.perform(post("/todo/v2/registration").contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(users1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).registerUser(any());
//    }

//    @Test
//    public void givenContentIdgetUser() throws Exception{
//        TodoList users=todoService.getListById("vinayaksolanki1923@gmail.com",2);
//        when(todoService.getListById("vinayaksolanki1923@gmail.com",2)).thenReturn(users);
//        mockMvc.perform(get("/todo/v2/user/getById/2").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(2)).getListById(any(),any());
//    }
//
//    @Mock
//    private TodoServiceImpl todoService;
//
//    @InjectMocks
//    private TodoController todoController;
//
//    private Users users1,users2;
//    private TodoList todoList;
//    List<TodoList> userlist;
//
//    @BeforeEach
//    void setUp(){
//        userlist=Arrays.asList(todoList);
//        todoList=new TodoList(2,"ddfs","jaja","23-12-2023","9:03","aws","wow","high");
//        users1=new Users("vinayaksolanki1923@gmail.com","vinayak","solanki","@Vinayak1233","@Vinayak1233","8630496822","","user","",userlist);
//        users2=new Users("vinayaksolanki1923@gmail.com","vinayakk","solankii","@Vinayak12333","@Vinayak12333","8630496822","","user","",userlist);
//        mockMvc= MockMvcBuilders.standaloneSetup(todoController).build();
//    }
//
////    @Test
////    public void givenUsertoregistration() throws Exception {
////        when(todoService.registerUser(any())).thenReturn(users1);
////        mockMvc.perform(post("/todo/v2/registration").contentType(MediaType.APPLICATION_JSON)
////                .content(jsonToString(users1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
////        verify(todoService,times(1)).registerUser(any());
////    }
//
////    @Test
////    public void givenContentIdgetUser() throws Exception{
////        TodoList users=todoService.getListById("vinayaksolanki1923@gmail.com",2);
////        when(todoService.getListById("vinayaksolanki1923@gmail.com",2)).thenReturn(users);
////        mockMvc.perform(get("/todo/v2/user/getById/2").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
////        verify(todoService,times(2)).getListById(any(),any());
////    }
////
////    @Test
////    public void givenContentIdDeleteUser() throws UserNotFound, ContentNotFound, Exception {
////        when(todoService.deleteList(2,"vinayaksolanki1923@gmail.com")).thenReturn(true,true);
////        mockMvc.perform(delete("/todo/v2/user/delete/2").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
////        verify(todoService,times(2)).deleteList(any(),any());
////    }
////
////
////    @Test
////    public void givenCustomerIdDeleteCustomer() throws Exception {
////        when(todoService.deleteList(2,"vinayaksolanki1923@gmail.com")).thenReturn(true);
////        mockMvc.perform(delete("/todo/v2/user/delete/2")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andDo(MockMvcResultHandlers.print());
////        verify(todoService,times(1)).deleteList(any(),any());
////
////    }
////
////    @Test
////    public void givenPrioritygetUser() throws Exception{
////        List<TodoList> user = todoService.getpriorityById("vinayaksolanki1923@gmail.com", "high");
////        when(todoService.getpriorityById("vinayaksolanki1923@gmail.com","high")).thenReturn(user);
////        mockMvc.perform(get("/todo/v2/user/getBysId/{priority}").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
////        verify(todoService,times(2)).getpriorityById("vinayaksolanki1923@gmail.com","high");
////    }
////
////    private static  String jsonToString(final Object object) throws JsonProcessingException {
////        String result;
////        try{
////            ObjectMapper mapper=new ObjectMapper();
////            String jsonContent=mapper.writeValueAsString(object);
////            result=jsonContent;
////
////        } catch (JsonProcessingException e) {
////            throw new RuntimeException(e);
////        }
////        return result;
////    }
//
//    @AfterEach
//    void tearDown()
//    {
//        users1=null;
//        users2=null;
//    }

}
