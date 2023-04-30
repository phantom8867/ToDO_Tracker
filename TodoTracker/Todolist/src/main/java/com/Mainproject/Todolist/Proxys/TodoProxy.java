package com.Mainproject.Todolist.Proxys;

import com.Mainproject.Todolist.domain.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service",url="http://user-auth-service:9002")
public interface TodoProxy {

    @PostMapping("/todo/v1/register")
    public ResponseEntity<?> saveUser(@RequestBody Users user);

    @PostMapping("/todo/v1/register/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody Users user);

    @PutMapping("/todo/v1/update")
    public ResponseEntity<?> updateUser(@RequestBody Users users);


}
