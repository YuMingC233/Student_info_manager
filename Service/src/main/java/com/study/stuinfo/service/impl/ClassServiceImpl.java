package com.study.stuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.stuinfo.dao.ClassDao;
import com.study.stuinfo.dao.GradeDao;
import com.study.stuinfo.entity.Class;
import com.study.stuinfo.entity.Grade;
import com.study.stuinfo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	ClassDao foo;
	@Autowired
	GradeDao grad;

	@Override
	public List<Class> getAllClass() {
		return foo.getAllClass();
	}

	@Override
	public Class getClassByID(Class cl) {
		return foo.getClass(cl);
	}

	@Override
	public List<Class> getClassList(Integer offset) {
		PageHelper.startPage(offset,10,true);
		List<Class> clList = foo.getAllClass();
		for (Class cItem:
			 clList) {
			Grade g = grad.getGrade(cItem.getGradeInf().getGradeID());
			cItem.setGradeInf(g);
		}
		return clList;
	}

	@Override
	public Boolean addClass(Class cl) {
		return foo.addClass(cl) > 0;
	}

	@Override
	public Boolean delClass(Class cl) {
		return foo.delClass(cl) > 0 ;
	}

	@Override
	public Boolean modifyClass(Class cl) {
		return foo.updClass(cl) > 0;
	}
}
