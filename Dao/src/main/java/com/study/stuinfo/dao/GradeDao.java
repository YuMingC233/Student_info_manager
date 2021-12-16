package com.study.stuinfo.dao;

import com.study.stuinfo.entity.Grade;

import java.util.List;

public interface GradeDao {
	List<Grade> getAllGrade();

	Grade getGrade(Integer ID);

	Integer addGrade(Grade gr);

	Integer updGrade(Grade gr);

	Integer delGrade(Grade gr);
}
