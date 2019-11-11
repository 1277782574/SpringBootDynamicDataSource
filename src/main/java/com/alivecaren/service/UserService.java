package com.alivecaren.service;


import com.alivecaren.model.User;

import java.util.List;

public interface UserService {

    List<User> findCloudDataSource();

    List<User> findTestDataSource();
}
