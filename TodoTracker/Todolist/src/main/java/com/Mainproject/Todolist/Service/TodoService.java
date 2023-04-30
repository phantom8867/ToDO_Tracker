package com.Mainproject.Todolist.Service;

import com.Mainproject.Todolist.Exception.ContentAlreadyExists;
import com.Mainproject.Todolist.Exception.ContentNotFound;
import com.Mainproject.Todolist.Exception.UserAlreadyExist;
import com.Mainproject.Todolist.Exception.UserNotFound;
import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Userinfo;
import com.Mainproject.Todolist.domain.Users;
import org.apache.catalina.User;

import java.util.List;
import java.util.TreeSet;

public interface TodoService {
    Users registerUser(Users users) throws UserAlreadyExist;
    public Users registerAdmin(Users users) throws UserAlreadyExist;
    Users addList(String email, TodoList todoList)throws UserNotFound, ContentAlreadyExists;
    Users updateList(TodoList todoList, String email, long contentid) throws UserNotFound, ContentNotFound;
    boolean deleteList(long contentid,String email)throws UserNotFound,ContentNotFound;
    List<TodoList> getList(String email)throws UserNotFound;
    TodoList getListById(String email,long id)throws UserNotFound,ContentNotFound;
    List<Users>  getallUser()throws UserNotFound;
    Users updatePassword(Users users,String email)throws UserNotFound;
    public Users getuserdetails(String email) throws UserNotFound;
    List<TodoList> getpriorityById(String email, String priority) throws UserNotFound;
    List<TodoList> getdateById(String email, String date) throws UserNotFound;
    TodoList getListBytitle(String email, String title) throws UserNotFound, ContentNotFound;
    Users updateUserprofile(Users users, String email) throws UserNotFound;
    List<TodoList> getcontentByStatus(String email, String status) throws UserNotFound;
    Users verification (String email, String verify) throws UserNotFound;
    long otpsender(String email) throws UserNotFound;
     String reqestfriend(String email, String friendEmail)throws  UserNotFound;
    String acceptfriend(String email,String friendEmail)throws  UserNotFound;
    boolean deletefriend(String email,String friendEmail)throws  UserNotFound;
    boolean cancelFriend(String email,String friendEmail)throws UserNotFound;
    Users getuserdetailsbyemail(String email)throws UserNotFound;
    String sharetodo(String email,String friendemail,TodoList todoList)throws UserNotFound;

    Users addFriendTodo(String email,String message,TodoList todoList)throws UserNotFound;
    List<Userinfo> emailstartwith(String email);
    boolean deleteFriendInRejected(String email,String friendEmail)throws UserNotFound;

    List<Userinfo> getFriendInfoByFriend(String email)throws UserNotFound;
    List<Userinfo> getFriendInfoByReject(String email)throws UserNotFound;
    List<Userinfo> getFriendInfoByRejected(String email)throws UserNotFound;
    List<TodoList> getInfoOfRequesttodolist (String email)throws UserNotFound;
}
