package com.MainProject.UserAuthentication.repository;

import com.MainProject.UserAuthentication.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
}
