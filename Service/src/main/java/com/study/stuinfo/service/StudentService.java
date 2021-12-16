package com.study.stuinfo.service;

import com.study.stuinfo.entity.Student;

import java.util.List;

public interface StudentService {
	List<Student> getAllList(Student stu, Integer offset);

	Boolean addStudent(Student stu);

	Boolean modifyStudent(Student stu);

	Student getStudent(String stuNo);

	Boolean delStudent(String NO);
}
