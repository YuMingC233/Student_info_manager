package com.study.stuinfo.entity;

import lombok.Data;

@Data
public class User {
	private Integer ID;
	private String Name;
	private String Password;

	public User(Integer ID, String name, String password) {
		this.ID = ID;
		Name = name;
		Password = password;
	}
}
