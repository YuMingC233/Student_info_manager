package com.study.stuinfo.dao;

import com.study.stuinfo.entity.User;

public interface UserDao {
	Integer Login(User u);

	Integer updPassWord(User u);

	User getUserInfByName(String Name);
}
