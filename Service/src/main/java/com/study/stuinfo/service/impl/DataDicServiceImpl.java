package com.study.stuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.stuinfo.dao.DataDicDao;
import com.study.stuinfo.dao.DataDicTypeDao;
import com.study.stuinfo.entity.Data_Dictionary;
import com.study.stuinfo.service.DataDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDicServiceImpl implements DataDicService {
	@Autowired
	DataDicDao foo;
	@Autowired
	DataDicTypeDao bar;

	@Override
	public List<Data_Dictionary> getAllDataDic() {
		return foo.getAllDataDic();
	}

	@Override
	public List<Data_Dictionary> getByTypeID(Integer TypeID) {
		return foo.getByTypeID(TypeID);
	}

	@Override
	public Data_Dictionary getByID(Integer ID) {
		return foo.getByID(ID);
	}

	@Override
	public List<Data_Dictionary> getDataDicList(Data_Dictionary dd, Integer offset) {
		PageHelper.startPage(offset, 8, true);
		List<Data_Dictionary> ddList = foo.getDataDicList(dd);
		for (Data_Dictionary ddItem:
				ddList) {
			ddItem.setTypeInf(bar.getDataDicType(ddItem.getTypeInf()));
		}
		return ddList;
	}

	@Override
	public Boolean addDataDic(Data_Dictionary dd) {
		return foo.addData_Dictionary(dd) > 0;
	}

	@Override
	public Boolean modifyDataDic(Data_Dictionary dd) {
		return foo.updData_Dictionary(dd) > 0;
	}

	@Override
	public Boolean delDataDic(Integer ID) {
		return foo.delData_Dictionary(ID) > 0;
	}
}
