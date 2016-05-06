package com.vincent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vincent.mapper.UserMapper;
import com.vincent.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private UserMapper userMapper;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUsername(username);
	}

}
