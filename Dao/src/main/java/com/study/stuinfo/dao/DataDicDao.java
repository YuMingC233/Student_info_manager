package com.study.stuinfo.dao;

import com.study.stuinfo.entity.Data_Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDicDao {
	List<Data_Dictionary> getAllDataDic();

	List<Data_Dictionary> getByTypeID(Integer TypeID);

	Data_Dictionary getByID(Integer ID);

	List<Data_Dictionary> getDataDicList(@Param("dd") Data_Dictionary dd);

	Integer addData_Dictionary(Data_Dictionary dd);

	Integer updData_Dictionary(Data_Dictionary dd);

	Integer delData_Dictionary(Integer ID);
}
