package com.MainProject.UserAuthentication.service;

import com.MainProject.UserAuthentication.domain.Users;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> tokenGenerator(Users user);
}
