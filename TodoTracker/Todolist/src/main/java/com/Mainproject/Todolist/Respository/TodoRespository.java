package com.Mainproject.Todolist.Respository;

import com.Mainproject.Todolist.domain.Userinfo;
import com.Mainproject.Todolist.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRespository extends MongoRepository<Users,String>{
    List<Userinfo> findByEmailStartingWith(String emailStr);
}
