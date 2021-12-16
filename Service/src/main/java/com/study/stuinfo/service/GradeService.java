package com.study.stuinfo.service;

import com.study.stuinfo.entity.Grade;

import java.util.List;

public interface GradeService {
	List<Grade> getAllGrade();

	List<Grade> getGradeList(Integer offset);

	Grade getGrade(Integer ID);

	Boolean addGrade(Grade gr);

	Boolean modifyGrade(Grade gr);

	Boolean delGrade(Grade gr);
}
