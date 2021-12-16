package com.study.stuinfo.service;

import com.study.stuinfo.entity.Data_Dictionary;

import java.util.List;

public interface DataDicService {
	List<Data_Dictionary> getAllDataDic();

	List<Data_Dictionary> getDataDicList(Data_Dictionary dd, Integer offset);

	List<Data_Dictionary> getByTypeID(Integer TypeID);

	Data_Dictionary getByID(Integer ID);

	Boolean addDataDic(Data_Dictionary dd);

	Boolean modifyDataDic(Data_Dictionary dd);

	Boolean delDataDic(Integer ID);
}
