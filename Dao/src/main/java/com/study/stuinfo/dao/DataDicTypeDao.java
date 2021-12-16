package com.study.stuinfo.dao;

import com.study.stuinfo.entity.Data_Dictionary_Type;

import java.util.List;

public interface DataDicTypeDao {
	List<Data_Dictionary_Type> getAllDataDicType();

	Data_Dictionary_Type getDataDicType(Data_Dictionary_Type ddt);

	Integer addDataDicType(Data_Dictionary_Type ddt);

	Integer updDataDicType(Data_Dictionary_Type ddt);

	Integer delDataDicType(Integer TypeID);
}
