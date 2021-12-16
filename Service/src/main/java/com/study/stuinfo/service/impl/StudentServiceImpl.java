package com.study.stuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.stuinfo.dao.ClassDao;
import com.study.stuinfo.dao.GradeDao;
import com.study.stuinfo.dao.StudentDao;
import com.study.stuinfo.entity.Student;
import com.study.stuinfo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentDao foo;
	@Autowired
	ClassDao cda;
	@Autowired
	GradeDao bar;

	@Override
	public List<Student> getAllList(Student stu, Integer offset) {
		PageHelper.startPage(offset, 5, true);
		List<Student> stuList = foo.getAllStu(stu);
		for (Student stuItem:
			stuList) {
			stuItem.setClassInf(cda.getClass(stuItem.getClassInf()));
			stuItem.setGradeInf(bar.getGrade(stuItem.getClassInf().getGradeInf().getGradeID()));
		}
		return stuList;
	}

	@Override
	public Boolean addStudent(Student stu) {
		return foo.addStudent(stu) > 0;
	}

	@Override
	public Boolean modifyStudent(Student stu) {
		return foo.updStudent(stu) > 0;
	}

	@Override
	public Student getStudent(String stuNo) {
		Student s = foo.getStudent(stuNo);
		s.setClassInf(cda.getClass(s.getClassInf()));
		return s;
	}

	@Override
	public Boolean delStudent(String NO) {
		return foo.delStudent(NO) > 0;
	}
}
