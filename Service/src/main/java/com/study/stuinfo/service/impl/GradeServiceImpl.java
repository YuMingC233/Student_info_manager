package com.study.stuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.stuinfo.dao.GradeDao;
import com.study.stuinfo.entity.Grade;
import com.study.stuinfo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	GradeDao foo;

	@Override
	public List<Grade> getAllGrade() {
		return foo.getAllGrade();
	}

	@Override
	public List<Grade> getGradeList(Integer offset) {
		PageHelper.startPage(offset,10,true);
		return foo.getAllGrade();
	}

	@Override
	public Grade getGrade(Integer ID) {
		return foo.getGrade(ID);
	}

	@Override
	public Boolean addGrade(Grade gr) {
		return foo.addGrade(gr) > 0;
	}

	@Override
	public Boolean modifyGrade(Grade gr) {
		return foo.updGrade(gr) > 0;
	}

	@Override
	public Boolean delGrade(Grade gr) {
		return foo.delGrade(gr) > 0;
	}


}
