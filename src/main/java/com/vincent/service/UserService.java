package com.vincent.service;

import com.vincent.model.User;

public interface UserService {

	User findByUsername(String username);

}
