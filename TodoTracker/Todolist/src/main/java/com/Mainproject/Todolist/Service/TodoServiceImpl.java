package com.Mainproject.Todolist.Service;

import com.Mainproject.Todolist.Exception.ContentAlreadyExists;
import com.Mainproject.Todolist.Exception.ContentNotFound;
import com.Mainproject.Todolist.Exception.UserAlreadyExist;
import com.Mainproject.Todolist.Exception.UserNotFound;
import com.Mainproject.Todolist.Proxys.TodoEmailProxy;
import com.Mainproject.Todolist.Proxys.TodoProxy;
import com.Mainproject.Todolist.Respository.TodoRespository;
import com.Mainproject.Todolist.domain.TodoList;
import com.Mainproject.Todolist.domain.Userinfo;
import com.Mainproject.Todolist.domain.Users;
import com.Mainproject.Todolist.rabbitmq.Produsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRespository todoRespository;
    @Autowired
    private TodoProxy todoProxy;
    @Autowired
    private Produsers produsers;
    @Autowired
    private TodoEmailProxy todoEmailProxy;
    @Override
    public Users registerUser(Users users) throws UserAlreadyExist {
        if (todoRespository.findById(users.getEmail()).isPresent()) {
            throw new UserAlreadyExist();
        }
        Users saveuser = todoRespository.save(users);
        if(!(saveuser.getEmail().isEmpty())){
            ResponseEntity responseEntity=todoProxy.saveUser(users);
        }
        produsers.sendverifyToQueue(users);
        return saveuser;
    }

    @Override
    public Users registerAdmin(Users users) throws UserAlreadyExist {
        if (todoRespository.findById(users.getEmail()).isPresent()) {
            throw new UserAlreadyExist();
        }
        Users saveuser = todoRespository.save(users);
        if(!(saveuser.getEmail().isEmpty())){
            ResponseEntity responseEntity=todoProxy.saveAdmin(users);
        }
        produsers.sendDtoToQueue(users);
        return saveuser;
    }



    @Override
    public Users addList(String email, TodoList todoList) throws UserNotFound, ContentAlreadyExists {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        Users users = todoRespository.findById(email).get();
//        produsers.sendTodoToQueue(todoList,users);
        users.getTodoList().add(todoList);
        return todoRespository.save(users);
    }

    @Override
    public Users updateList(TodoList todoList, String email,long contentid) throws UserNotFound, ContentNotFound {
        if (todoRespository.findById(email).isPresent()) {
            Users users = todoRespository.findById(email).get();
            List<TodoList> todoLists = users.getTodoList();
            List<Users> usersList = null;
            for (TodoList todo : todoLists) {
                if (todo.getContentid()==contentid) {
                    if (todoList.getTitle() != null) {
                        todo.setTitle(todoList.getTitle());
                    }
                    if (todoList.getDescription() != null) {
                        todo.setDescription(todoList.getDescription());
                    }
                    if (todoList.getDate() != null) {
                        todo.setDate(todoList.getDate());
                    }
                    if (todoList.getTime() != null) {
                        todo.setTime(todoList.getTime());
                    }
                    if (todoList.getUrl() != null) {
                        todo.setUrl(todoList.getUrl());
                    }
                    if (todoList.getPriority() != null) {
                        todo.setPriority(todoList.getPriority());
                    }
                    if (todoList.getStatus() != null) {
                        todo.setStatus(todoList.getStatus());
                    }
                    users.setTodoList(todoLists);
                    break;
                }
            }
            return todoRespository.save(users);
        } else throw new UserNotFound();
    }

    @Override
    public boolean deleteList(long contentid, String email) throws UserNotFound, ContentNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        boolean flag = false;
        Users users = todoRespository.findById(email).get();
        List<TodoList> todoLists = users.getTodoList();
        for (TodoList todoList : todoLists) {
            if (todoList.getContentid()==contentid) {
                todoLists.remove(todoList);
                users.setTodoList(todoLists);
                todoRespository.save(users);
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<TodoList> getList(String email) throws UserNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();
        return todoLists;
    }
    @Override
    public Users getuserdetails(String email) throws UserNotFound{
        if(todoRespository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        System.out.println("details");
        Users users=todoRespository.findById(email).get();
        System.out.println(users);
        return users;
    }


    @Override
    public TodoList getListById(String email, long contentid) throws UserNotFound, ContentNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        TodoList todoList;
        TodoList todoList1 = null;
        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();

        for (TodoList list : todoLists) {
            if (list.getContentid()==contentid) {
                todoList1 = list;
                break;
            }
        }
        return todoList1;
    }
    @Override
    public TodoList getListBytitle(String email, String title) throws UserNotFound, ContentNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        TodoList todoList1=null;
        System.out.println(title);
        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();
        System.out.print(todoLists);
        for (TodoList list : todoLists) {
            System.out.println(title);
            if (list.getTitle().equals(title)) {
                todoList1 = list;
                System.out.println(todoList1);
                break;
            }
        }
        return todoList1;
    }
    public List<TodoList> getcontentByStatus(String email, String status) throws UserNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        List<TodoList> status1 = new ArrayList<>();

        System.out.println(status);

        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();
        System.out.println(todoLists);

        for (TodoList list : todoLists) {
            System.out.println(list.getStatus());

            if (list.getStatus().equals(status)) {
                System.out.println(list);
                status1.add(list);
                System.out.println(status1);
            }
        }
        return status1;
    }



    public List<TodoList> getpriorityById(String email, String priority) throws UserNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        System.out.println(priority);
        List<TodoList> prioritys=new ArrayList<>();
        System.out.println(prioritys);
        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();
        System.out.println(todoLists);
        for (TodoList list : todoLists) {
            System.out.println(list);
            if (list.getPriority().equals(priority)) {
                System.out.println(list);
                prioritys.add(list);
                System.out.println(prioritys);
            }
        }
        return prioritys;
    }
    public List<TodoList> getdateById(String email, String date) throws UserNotFound {
        if (todoRespository.findById(email).isEmpty()) {
            throw new UserNotFound();
        }
        List<TodoList> dates=new ArrayList<>();

        List<TodoList> todoLists = todoRespository.findById(email).get().getTodoList();
        System.out.println(todoLists);
        for (TodoList list : todoLists) {
            System.out.println(list);
            if (list.getDate().equals(date)) {
                System.out.println(list);
                dates.add(list);
                System.out.println(dates);
            }
        }
        return dates;
    }
    @Override
    public List<Users> getallUser() throws UserNotFound {
        List<Users> usersList = todoRespository.findAll();
        return usersList;
    }

    @Override
    public Users updatePassword(Users users, String email) throws UserNotFound {
        Users users1;
        if (todoRespository.findById(email).isPresent()) {
            Users user = todoRespository.findById(email).get();
            if (user.getEmail().equals(email)) {

                if (user.getPassword()!= null) {
                    user.setPassword(users.getPassword());
                }

            }
            users1=todoRespository.save(user);
            if(!(users1.getEmail().isEmpty())){
                ResponseEntity responseEntity=todoProxy.updateUser(users1);
                produsers.sendpasswordToQueue(users1);
            }

        return users1;
    }
        else throw new UserNotFound();
    }

    @Override
    public Users verification(String email, String verify) throws UserNotFound {
        Users users1;
        if(todoRespository.findById(email).isPresent()){
            Users users=todoRespository.findById(email).get();
            if(users.getEmail().equals(email)){
                if(users.getVerify().equals("NOT")){
                    users.setVerify(verify);
                }
            }
            users1 =todoRespository.save(users);
            if(!(users1.getEmail().isEmpty())){
                ResponseEntity responseEntity=todoProxy.updateUser(users);
                produsers.sendDtoToQueue(users);
            }
            return users1;
        }else {
            throw new UserNotFound();
        }
    }

    @Override
    public Users updateUserprofile(Users users, String email) throws UserNotFound {
        Users users1;
        if (todoRespository.findById(email).isPresent()) {
            Users user = todoRespository.findById(email).get();
            if (user.getEmail().equals(email)) {
                if (users.getProfileimg() != null) {
                    user.setProfileimg(users.getProfileimg());
                }
                if (users.getFirstname() != null) {
                    user.setFirstname(users.getFirstname());
                }
                if (users.getPassword() != null) {
                    user.setPassword(users.getPassword());
                }
                if (users.getPhonenumber() != null) {
                    user.setPhonenumber(users.getPhonenumber());
                }


                if (users.getSuggestion() != null) {
                    user.setSuggestion(users.getSuggestion());
                }
            }
            users1=todoRespository.save(user);


            return users1;
        }
        else throw new UserNotFound();
    }
    @Override
    public  Users getuserdetailsbyemail(String email)throws UserNotFound{
        Users user = todoRespository.findById(email).get();
        return user;
    }

    @Override
    public long otpsender(String email)throws UserNotFound{
        long otp=0;
        if(todoRespository.findById(email).isEmpty()){
            throw new UserNotFound();
        }else{
            do {
                otp = new Random().nextInt(999999);
            }while (otp<=99999);
            System.out.println(otp);
            String otp1= String.valueOf(otp);
            Users users=todoRespository.findById(email).get();
            users.setOtp(otp1);
            System.out.println("OTP IS : "+users.getOtp());
            produsers.sendOtpToQueue(users);
//            todoEmailProxy.sendotp(users);
            return otp;
        }
    }

    @Override
    public String reqestfriend(String email,String senterEmail) throws UserNotFound {

        if(todoRespository.findById(email).isEmpty() && todoRespository.findById(senterEmail).isEmpty()){
            return "No   user Found";}
        else {


            Users senter = todoRespository.findById(senterEmail).get();
            System.out.println("senter mail"+senter.getEmail());
            Users users = todoRespository.findById(email).get();
            System.out.println("User mail"+users.getEmail());
            Userinfo temp = new Userinfo(senter.getEmail(), senter.getFirstname(), senter.getProfileimg());
            System.out.println("userinfo"+temp.getEmail());
            if(users.getRequested()==null){
                users.setRequested(List.of(temp));
            }else{
                List<Userinfo> data = users.getRequested();
                System.out.println("Data"+data);
                data.add(temp);
                users.setRequested(data);
            }
            todoRespository.save(users);
            Userinfo temp2 = new Userinfo(users.getEmail(), users.getFirstname(), users.getProfileimg());
            if(senter.getRequest()==null){
                senter.setRequest(List.of(temp2));
            }else {
                List<Userinfo> data1 = senter.getRequest();
                data1.add(temp2);
                senter.setRequest(data1);
            }                                  //prabu is senter request to selvam
                                              //prabu is a requested to selvam to be freined
                                              //selvam get request from prabu
            todoRespository.save(senter);
            return "requested sent successfully";
        }
    }

    @Override
    public String acceptfriend(String email, String senteremail) throws UserNotFound {

        if(todoRespository.findById(email).isPresent()) {
            Users reciver=todoRespository.findById(email).get();
            Users senter = todoRespository.findById(senteremail).get();
            System.out.println(senter.getEmail());
            List<Userinfo> senters =senter.getRequested();   //email is selvam and prabu is senteremail
                                                              //senteremail have requested is removed  store to senter
            System.out.println(senters);
            senters.removeIf(users1 -> users1.getEmail().equals(email));
            System.out.println(senters);
            Userinfo temp2=new Userinfo(reciver.getEmail(), reciver.getFirstname(), reciver.getProfileimg());
            System.out.println(temp2.getEmail());
            if(senter.getFriends()==null){
                senter.setFriends(List.of(temp2));
            }else {
                List<Userinfo> data1 = senter.getFriends();
                data1.add(temp2);
                senter.setFriends(data1);
                System.out.println(senter.getFriends());
            }
            todoRespository.save(senter);



            System.out.println(reciver.getEmail());
            List<Userinfo> senter1=reciver.getRequest();   //email is selvam and prabu is senteremail
            //senteremail have requested is removed  store to senter
            System.out.println(senter1);
            senter1.removeIf(users11 -> users11.getEmail().equals(senteremail));
            System.out.println(senter1);
            Userinfo temp=new Userinfo(senter.getEmail(), senter.getFirstname(),senter.getProfileimg());
            if(reciver.getFriends()==null){
                reciver.setFriends(List.of(temp));
            }else {
                List<Userinfo> data2 = reciver.getFriends();
                data2.add(temp);
                System.out.println(data2);
                reciver.setFriends(data2);
                System.out.println(reciver.getFriends());
            }
            todoRespository.save(reciver);


        }
        return "senter Accepted";
    }

    @Override
    public boolean deletefriend(String email, String senteremail) throws UserNotFound {
        boolean flag = false;
        if(todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(senteremail).get();
            System.out.println(senter);
            List<Userinfo> senters = senter.getFriends();
            System.out.println(senter);
            senters.removeIf(users1 -> users1.getEmail().equals(email));
            senter.setFriends(senters);
            todoRespository.save(senter);


            Users users1=todoRespository.findById(email).get();
            List<Userinfo> senter1=users1.getFriends();
            System.out.println(senter1);
            senter1.removeIf(users11 -> users11.getEmail().equals(senteremail));
            users1.setFriends(senter1);
            todoRespository.save(users1);
            flag=true;

        }
        else{
            flag=false;
        }
        return flag;

    }

    @Override
    public boolean cancelFriend(String email, String senterEmail) throws UserNotFound {
        boolean flag = false;
        if(todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(senterEmail).get();
            System.out.println(senter);
            List<Userinfo> senters = senter.getRequested();
            System.out.println(senter);
            senters.removeIf(users1 -> users1.getEmail().equals(email));
            senter.setRequested(senters);
            todoRespository.save(senter);


            Users users1=todoRespository.findById(email).get();
            List<Userinfo> senter1=users1.getRequest();
            System.out.println(senter1);
            senter1.removeIf(users11 -> users11.getEmail().equals(senterEmail));
            users1.setRequest(senter1);
            todoRespository.save(users1);
            flag=true;

        }
        else{
            flag=false;
        }
        return flag;
    }



    @Override
    public boolean deleteFriendInRejected(String email, String friendEmail) throws UserNotFound {
        boolean flag=false;
        if(todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(email).get();
            System.out.println(senter);
            List<Userinfo> senters = senter.getRequested();
            System.out.println(senter);
            senters.removeIf(users1 -> users1.getEmail().equals(friendEmail));
            senter.setRequested(senters);
            todoRespository.save(senter);

            Users recever = todoRespository.findById(friendEmail).get();
            System.out.println(recever);
            List<Userinfo> recever1 = senter.getRequest();
            System.out.println(senter);
            recever1.removeIf(users1 -> users1.getEmail().equals(email));
            recever.setRequest(recever1);
            todoRespository.save(recever);

            flag=true;
        }else{
            flag=false;
        }
        return flag;

    }

    @Override
    public List<Userinfo> getFriendInfoByFriend(String email) throws UserNotFound {
        List<Userinfo> getfriendlist = null;
        if (todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(email).get();
            getfriendlist = senter.getFriends();
        }

        return getfriendlist;
    }

    @Override
    public List<Userinfo> getFriendInfoByReject(String email) throws UserNotFound {
        List<Userinfo> getreguestlist = null;
        if (todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(email).get();
            getreguestlist = senter.getRequest();
        }

        return getreguestlist;

    }

    @Override
    public List<Userinfo> getFriendInfoByRejected(String email) throws UserNotFound {
        List<Userinfo> getreguestedlist = null;
        if (todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(email).get();
            getreguestedlist = senter.getRequested();
        }

        return getreguestedlist;
    }

    @Override
    public List<TodoList> getInfoOfRequesttodolist(String email) throws UserNotFound {
        List<TodoList> getreguestedtodo = null;
        if (todoRespository.findById(email).isPresent()) {
            Users senter = todoRespository.findById(email).get();
            getreguestedtodo = senter.getRequesttodolist();
        }

        return getreguestedtodo;

    }


    @Override
    public String sharetodo(String email,String senteremail,TodoList todoList)throws UserNotFound{
        Users users = todoRespository.findById(senteremail).get();
        todoList.setSendby(email);
        if(users.getRequesttodolist()==null){
            users.setRequesttodolist(List.of(todoList));
            todoRespository.save(users);
        }else {
            
            List<TodoList> lists=users.getRequesttodolist();
            lists.add(todoList);
            users.setRequesttodolist(lists);
            todoRespository.save(users);
        }
        return "task sended succesfully";
    }

    @Override
    public Users addFriendTodo(String email, String message, TodoList todoList) throws UserNotFound {
        Users users = todoRespository.findById(email).get();
        if(message.equals("Accept")){
            List<TodoList> lists=users.getRequesttodolist();
            lists.removeIf(users1 -> users1.equals(todoList));
            List<TodoList> todo=users.getTodoList();
            todo.add(todoList);
            users.setTodoList(todo);
            todoRespository.save(users);
        }else{
            List<TodoList> delete=users.getRequesttodolist();
            delete.removeIf(users1 -> users1.equals(todoList));
            users.setRequesttodolist(delete);
            todoRespository.save(users);
        }
        return users;
    }


    @Override
    public List<Userinfo> emailstartwith(String email) {
        List<Userinfo> list=todoRespository.findByEmailStartingWith(email);
        System.out.println(list);
        TreeSet<Userinfo> userinfos=new TreeSet<>();
        list.sort(new Comparator<Userinfo>() {
            public int compare(Userinfo o1, Userinfo o2) {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        });
//        for(Userinfo user:list){
//            System.out.println("user"+user);
//            userinfos.add(user);
//            System.out.println("userinfo"+userinfos);
//        }
        System.out.println("Finally"+list);
        return list;
    }
}
