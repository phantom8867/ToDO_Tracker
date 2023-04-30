package com.Mainproject.Todolist.Controller;

import com.Mainproject.Todolist.Exception.ContentAlreadyExists;
import com.Mainproject.Todolist.Exception.ContentNotFound;
import com.Mainproject.Todolist.Exception.UserAlreadyExist;
import com.Mainproject.Todolist.Exception.UserNotFound;
import com.Mainproject.Todolist.Respository.TodoRespository;
import com.Mainproject.Todolist.Service.TodoService;
import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Users;
import com.Mainproject.Todolist.rabbitmq.Produsers;
import com.sun.source.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("/todo/v2/")
public class TodoController {
    private ResponseEntity responseEntity;

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRespository todoRespository;
    @Autowired
    private Produsers produsers;

    @PostMapping("registration")
    public ResponseEntity<?> registerUser(@RequestBody Users users)throws UserAlreadyExist{
       try{
           users.setTodoList(new ArrayList<>());
           users.setRole("user");
           users.setVerify("NOT");
           responseEntity= new ResponseEntity<>(todoService.registerUser(users), HttpStatus.CREATED);
       }catch (UserAlreadyExist e){
           throw new UserAlreadyExist();
       }
       return responseEntity;
   }

    @PostMapping("registration/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Users users)throws UserAlreadyExist{
        try{
            users.setTodoList(new ArrayList<>());
            users.setRole("admin");
            users.setVerify("OK");
            responseEntity= new ResponseEntity<>(todoService.registerAdmin(users), HttpStatus.CREATED);
        }catch (UserAlreadyExist e){
            throw new UserAlreadyExist();
        }
        return responseEntity;
    }

    @PutMapping("verify/{email}")
    public ResponseEntity<?> emailverification(@PathVariable String email, @RequestBody String verify)throws UserNotFound{
        try {
               responseEntity=new ResponseEntity<>(todoService.verification(email, verify),HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @GetMapping("otp/{email}")
    public ResponseEntity<?> otpSender(@PathVariable String email)throws UserNotFound
    {
        try{
            responseEntity=new ResponseEntity<>(todoService.otpsender(email),HttpStatus.OK);

        }catch (Exception e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @PostMapping("user/addlist")
    public ResponseEntity<?> addlist(@RequestBody TodoList todoList, HttpServletRequest request) throws UserNotFound, ContentAlreadyExists {
        try{
            todoList.setStatus("Active");
            String email=(String) request.getAttribute("current user email");
            responseEntity=new ResponseEntity(todoService.addList(email,todoList),HttpStatus.OK);
        }catch (UserNotFound e){
            System.out.println("User Not Found");
            throw new UserNotFound();
        }
        catch(ContentAlreadyExists e){
            throw new ContentAlreadyExists();
        }
        return responseEntity;
    }

    @PutMapping("user/update/{contentid}")
    public ResponseEntity<?> updateContent(@RequestBody TodoList todoList,@PathVariable long contentid, HttpServletRequest request) throws ContentNotFound,UserNotFound{
        try{
            String email=(String) request.getAttribute("current user email");
            String contentids= Long.toString(contentid);
            responseEntity= new ResponseEntity(todoService.updateList(todoList,email,contentid),HttpStatus.CREATED);
        }catch (ContentNotFound e){
            throw new ContentNotFound();
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @DeleteMapping("user/delete/{contentid}")
    public ResponseEntity<?> deleteContent(@PathVariable long contentid, HttpServletRequest request)throws ContentNotFound {
        try{
            String email=(String) request.getAttribute("current user email");
            String contentids= Long.toString(contentid);
            responseEntity=new ResponseEntity(todoService.deleteList(contentid,email),HttpStatus.OK);
        }catch (ContentNotFound e){
            throw new ContentNotFound();
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @GetMapping("user/Userdetails")
    public ResponseEntity<?> getAllContent(HttpServletRequest request)throws UserNotFound{
        try{
            List<TodoList> todoLists;
            String alpha=(String) request.getAttribute("current user email");
            todoLists= todoService.getList(alpha);
            responseEntity=new ResponseEntity(todoLists,HttpStatus.OK);
        }catch (UserNotFound e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @GetMapping("user/getById/{contentid}")
    public ResponseEntity<?> getContentById(@PathVariable long contentid,HttpServletRequest request)throws ContentNotFound{
        try{
            TodoList todoList;
            String alpha=(String) request.getAttribute("current user email");
            String contentids= Long.toString(contentid);
            todoList = todoService.getListById(alpha,contentid);
            responseEntity=new ResponseEntity(todoList,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @GetMapping("user/getBysId/{priority}")
    public ResponseEntity<?> getContenstById(@PathVariable String priority,HttpServletRequest request)throws UserNotFound{
        try{
            List<TodoList> todoList;
            String alpha=(String) request.getAttribute("current user email");
            todoList = todoService.getpriorityById(alpha,priority);
            responseEntity=new ResponseEntity(todoList,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("user/getStatus/{status}")
    public ResponseEntity<?> getstatustById(@PathVariable String status,HttpServletRequest request)throws UserNotFound{
        try{
            List<TodoList> todoList;
            String alpha=(String) request.getAttribute("current user email");

            todoList = todoService.getcontentByStatus(alpha,status);

            responseEntity=new ResponseEntity(todoList,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("user/getTitle/{title}")
    public ResponseEntity<?> gettitle(@PathVariable String title,HttpServletRequest request)throws UserNotFound, ContentNotFound{
        try{
            TodoList todoList;
            String alpha=(String) request.getAttribute("current user email");
            System.out.println("controller1");
            todoList = todoService.getListBytitle(alpha,title);
            System.out.println("controller2");
            responseEntity=new ResponseEntity(todoList,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("user/getByssId/{date}")
    public ResponseEntity<?> getdateById(@PathVariable String date,HttpServletRequest request)throws UserNotFound{
        try{
            List<TodoList> todoList;
            String alpha=(String) request.getAttribute("current user email");
            todoList = todoService.getdateById(alpha,date);
            responseEntity=new ResponseEntity(todoList,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("admin/alluser")
    public ResponseEntity<?> getallUser(HttpServletRequest request){
        try{
            String role=(String) request.getAttribute("current_role");
            if(role.equals("admin")) {
                List<Users> userlist;
                userlist = todoService.getallUser();
                responseEntity = new ResponseEntity(userlist, HttpStatus.OK);
            }
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @PutMapping("updatepassword/{email}")
    public ResponseEntity<?> updateuser(@RequestBody Users users,@PathVariable String email, HttpServletRequest request) throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            responseEntity= new ResponseEntity(todoService.updatePassword(users,email),HttpStatus.CREATED);
        }catch (UserNotFound e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @PutMapping("user/updateuserprofile")
    public ResponseEntity<?> updateuserprofile(@RequestBody Users users, HttpServletRequest request) throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            responseEntity= new ResponseEntity(todoService.updateUserprofile(users,alpha),HttpStatus.CREATED);
        }catch (UserNotFound e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @GetMapping("user/getuser")
    public ResponseEntity<?> getUserbyemail(HttpServletRequest request)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            Users userlist=todoService.getuserdetails(alpha);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @PostMapping("user/addfriend/{email}")
    public ResponseEntity<?> addfriend(@PathVariable String email,HttpServletRequest request)throws UserNotFound{
        try{
            System.out.println(request);
            String alpha=(String) request.getAttribute("current user email");
            String userlist=todoService.reqestfriend(alpha,email);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @PostMapping("user/acceptfriend/{email}")
    public ResponseEntity<?> acceptfriend(HttpServletRequest request,@PathVariable String email)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            String userlist=todoService.acceptfriend(alpha,email);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @DeleteMapping("user/deletefriend/{email}")
    public ResponseEntity<?> deletefriend(HttpServletRequest request,@PathVariable String email)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
//            String userlist=todoService.deletefriend(alpha,email);
            responseEntity=new ResponseEntity(todoService.deletefriend(alpha,email),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @DeleteMapping("user/deleterequest/{email}")
    public ResponseEntity<?> deleterequest(HttpServletRequest request,@PathVariable String email)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
//            String userlist=todoService.cancelFriend(alpha,email);
            responseEntity=new ResponseEntity(todoService.cancelFriend(alpha,email),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @PostMapping("user/sharetodo/{email}")
    public ResponseEntity<?> sharetodo(@RequestBody TodoList todoList,HttpServletRequest request,@PathVariable String email)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            String userlist=todoService.sharetodo(alpha,email,todoList);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @GetMapping("user/getfriends/{email}")
    public ResponseEntity<?> getfriendbyemail(@PathVariable String email){
            responseEntity=new ResponseEntity<>(todoService.emailstartwith(email),HttpStatus.OK);
            return responseEntity;
    }

    @GetMapping("user/getinfofriends/{email}")
    public ResponseEntity<?> getInfofriend(@PathVariable String email)throws UserNotFound{
        responseEntity=new ResponseEntity<>(todoService.getFriendInfoByFriend(email),HttpStatus.OK);
        return responseEntity;
    }@GetMapping("user/getinforeject/{email}")
    public ResponseEntity<?> getInfoofrejected(@PathVariable String email)throws UserNotFound{
        responseEntity=new ResponseEntity<>(todoService.getFriendInfoByReject(email),HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("user/getinforejected/{email}")
    public ResponseEntity<?> getInfrejected(@PathVariable String email)throws UserNotFound{
        responseEntity=new ResponseEntity<>(todoService.getFriendInfoByRejected(email),HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("user/deleterequested/{email}")
    public ResponseEntity<?> deleterequested(HttpServletRequest request,@PathVariable String email)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
//            String userlist=todoService.deleteFriendInRejected(alpha,email);
            responseEntity=new ResponseEntity(todoService.deleteFriendInRejected(alpha,email),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }


    @PostMapping("user/addtodotofriends/{message}")
    public ResponseEntity<?> addtodofriend(@RequestBody TodoList todoList,HttpServletRequest request,@PathVariable String message)throws UserNotFound{
        try{
            String alpha=(String) request.getAttribute("current user email");
            Users userlist=todoService.addFriendTodo(alpha,message,todoList);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }



    @GetMapping("user/getinforejectedtodolist/{email}")
    public ResponseEntity<?> getInfrejectedtodolist(@PathVariable String email)throws UserNotFound{
        responseEntity=new ResponseEntity<>(todoService.getInfoOfRequesttodolist(email),HttpStatus.OK);
        return responseEntity;
    }



}
