package com.study.stuinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Data_Dictionary {
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("Value")
	private String Value;
	@JsonProperty("Desc")
	private String Desc;

	@JsonProperty("typeInf")
	private Data_Dictionary_Type typeInf;
}
