package com.study.stuinfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Data_Dictionary_Type {
	@JsonProperty("TypeID")
	private Integer TypeID;
	@JsonProperty("TypeName")
	private String TypeName;
	@JsonProperty("TypeDesc")
	private String TypeDesc;
}
