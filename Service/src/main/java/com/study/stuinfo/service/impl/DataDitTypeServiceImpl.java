package com.study.stuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.stuinfo.dao.DataDicTypeDao;
import com.study.stuinfo.entity.Data_Dictionary_Type;
import com.study.stuinfo.service.DataDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDitTypeServiceImpl implements DataDicTypeService {
	@Autowired
	DataDicTypeDao foo;

	@Override
	public List<Data_Dictionary_Type> getAllDataDicType() {
		return foo.getAllDataDicType();
	}

	@Override
	public Data_Dictionary_Type getDataDicType(Data_Dictionary_Type ddt) {
		return foo.getDataDicType(ddt);
	}

	@Override
	public List<Data_Dictionary_Type> getDataDicTypeList(Integer offset) {
		PageHelper.startPage(offset,10,true);
		return foo.getAllDataDicType();
	}

	@Override
	public boolean addDataDicType(Data_Dictionary_Type ddt) {
		return foo.addDataDicType(ddt) > 0;
	}

	@Override
	public boolean updDataDicType(Data_Dictionary_Type ddt) {
		return foo.updDataDicType(ddt) > 0;
	}

	@Override
	public boolean delDataDicType(Integer TypeID) {
		return foo.delDataDicType(TypeID) > 0;
	}
}
