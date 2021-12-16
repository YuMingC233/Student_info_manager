package com.study.stuinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Grade {
	@JsonProperty("GradeID")
	private Integer GradeID;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Desc")
	private String Desc;
}
