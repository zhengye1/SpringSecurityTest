package com.vincent.dao;

import com.vincent.model.User;

public interface UserDAO {
	User findByUsername(String username);
}
