package com.enterpriseai.user.service;

import com.enterpriseai.user.dto.UserProfileResponse;

public interface UserService {

    UserProfileResponse getCurrentUser();

}