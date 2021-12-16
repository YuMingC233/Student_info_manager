package com.study.stuinfo.service;

import com.study.stuinfo.entity.Data_Dictionary_Type;

import java.util.List;

public interface DataDicTypeService {
	List<Data_Dictionary_Type> getAllDataDicType();

	List<Data_Dictionary_Type> getDataDicTypeList(Integer offset);

	Data_Dictionary_Type getDataDicType(Data_Dictionary_Type ddt);

	boolean addDataDicType(Data_Dictionary_Type ddt);

	boolean updDataDicType(Data_Dictionary_Type ddt);

	boolean delDataDicType(Integer TypeID);
}
