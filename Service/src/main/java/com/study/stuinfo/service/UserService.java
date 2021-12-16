package com.study.stuinfo.service;

import com.study.stuinfo.entity.User;

public interface UserService {
	boolean Login(User u);

	Boolean updPassWord(User u);

	User getUserInfByName(String Name);
}
