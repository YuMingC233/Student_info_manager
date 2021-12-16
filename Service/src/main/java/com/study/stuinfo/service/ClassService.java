package com.study.stuinfo.service;

import com.study.stuinfo.entity.Class;

import java.util.List;

public interface ClassService {
	List<Class> getAllClass();

	Class getClassByID(Class cl);

	List<Class> getClassList(Integer offset);

	Boolean addClass(Class cl);

	Boolean delClass(Class cl);

	Boolean modifyClass(Class cl);
}
