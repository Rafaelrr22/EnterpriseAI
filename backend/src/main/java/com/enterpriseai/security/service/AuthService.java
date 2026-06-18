package com.enterpriseai.security.service;

import com.enterpriseai.security.dto.LoginRequest;
import com.enterpriseai.security.dto.LoginResponse;
import com.enterpriseai.security.dto.RegisterRequest;
import com.enterpriseai.security.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
