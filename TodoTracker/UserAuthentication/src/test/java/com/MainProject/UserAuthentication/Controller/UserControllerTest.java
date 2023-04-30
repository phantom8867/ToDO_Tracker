package com.MainProject.UserAuthentication.Controller;

import com.MainProject.UserAuthentication.controller.UserController;
import com.MainProject.UserAuthentication.domain.Users;
import com.MainProject.UserAuthentication.service.UserServiceImpl;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private Users users1,users2;

    List<Users> userlist;

    @BeforeEach
    void setUp(){
        users1=new Users("prabu@gmail.com","prabu123", "OK","user");
        users2=new Users("prabu@gmail.com" ,"prabu1223","OK","user");
        userlist= Arrays.asList(users1,users2);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void givenUsertoregistration() throws Exception {
        when(userService.addUser(any())).thenReturn(users1);
        mockMvc.perform(post("/todo/v1/register").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(users1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).addUser(any());
    }



    @Test
    public void givenTrackIdgetUser() throws Exception{
        List<Users> users=userService.getAllUser();
        when(userService.getAllUser()).thenReturn(users);
        mockMvc.perform(get("/todo/v1/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(2)).getAllUser();
    }

    @Test
    public void givenTrackIdDeleteCustomer() throws Exception{
        when(userService.deleteUser(any())).thenReturn("user deleted successfully");
        mockMvc.perform(delete("/todo/v1/delete/prabu@gmail.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).deleteUser(any());
    }

    private static  String jsonToString(final Object object) throws JsonProcessingException {
        String result;
        try{
            ObjectMapper mapper=new ObjectMapper();
            String jsonContent=mapper.writeValueAsString(object);
            result=jsonContent;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    @AfterEach
    void tearDowt()
    {
        users1=null;
        users2=null;
    }

}
