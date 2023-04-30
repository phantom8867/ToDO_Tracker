package com.Mainproject.Todolist.Proxys;

import com.Mainproject.Todolist.domain.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notification-service",url = "http://notification-service:9003")
public interface TodoEmailProxy {
    @GetMapping("todo/v3/notify/otp")
    public ResponseEntity<?> sendotp(@RequestBody Users users);


}
