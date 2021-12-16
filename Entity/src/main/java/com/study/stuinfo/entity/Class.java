package com.study.stuinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Class {
	@JsonProperty("ClassID")
	private Integer ClassID;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("ClassDesc")
	private String ClassDesc;


	@JsonProperty("gradeInf")
	private Grade gradeInf;
}