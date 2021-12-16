package com.study.stuinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
	@JsonProperty("NO")
	private String NO;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Sex")
	private String Sex;
	@JsonProperty("Birthday")
	private Date Birthday;
	@JsonProperty("Birthday_Begin")
	private Date Birthday_Begin;
	@JsonProperty("Birthday_Over")
	private Date Birthday_Over;
	@JsonProperty("Admission_Time")
	private Date Admission_Time;
	@JsonProperty("Admission_Time_Begin")
	private Date Admission_Time_Begin;
	@JsonProperty("Admission_Time_Over")
	private Date Admission_Time_Over;
	@JsonProperty("Political_Appearance")
	private String Political_Appearance;
	@JsonProperty("Nation")
	private String Nation;
	@JsonProperty("Desc")
	private String Desc;
	@JsonProperty("Pic")
	private String Pic;
	@JsonProperty("classInf")
	private Class classInf;
	@JsonProperty("gradeInf")
	private Grade gradeInf;
}
