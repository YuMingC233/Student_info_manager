package com.study.stuinfo.dao;

import com.study.stuinfo.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
	List<Student> getAllStu(@Param("stu") Student stu);

	Integer addStudent(Student stu);

	Student getStudent(String StuNo);

	Integer updStudent(Student stu);

	Integer delStudent(String NO);
}
