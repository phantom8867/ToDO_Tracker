package com.mainproject.NotificationService.Repository;

import com.mainproject.NotificationService.Domain.UsersDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepos extends MongoRepository<UsersDTO,String> {
    UsersDTO findByEmail(String email);
}
