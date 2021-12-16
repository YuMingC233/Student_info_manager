package com.study.stuinfo.dao;

import com.study.stuinfo.entity.Class;

import java.util.List;

public interface ClassDao {
	List<Class> getAllClass();

	Class getClass(Class cl);

	Integer addClass(Class cl);

	Integer updClass(Class cl);

	Integer delClass(Class cl);
}
