package com.study.stuinfo.service.impl;

import com.study.stuinfo.dao.UserDao;
import com.study.stuinfo.entity.User;
import com.study.stuinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao foo;

	@Override
	public boolean Login(User u) {
		return foo.Login(u) > 0;
	}

	@Override
	public Boolean updPassWord(User u) {
		return foo.updPassWord(u) > 0;
	}

	@Override
	public User getUserInfByName(String Name) {
		return foo.getUserInfByName(Name);
	}
}
