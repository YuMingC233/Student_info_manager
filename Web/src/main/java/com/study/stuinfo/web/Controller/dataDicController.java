package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.study.stuinfo.entity.Data_Dictionary;
import com.study.stuinfo.service.DataDicService;
import com.study.stuinfo.service.DataDicTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class dataDicController {
	@Resource
	DataDicService dataDicS;
	@Resource
	DataDicTypeService dataTypeS;

	@RequestMapping(value = {"/dataDicPage"}, method = RequestMethod.GET)
	public String getDataDicInfList(HttpSession session,
								HttpServletRequest req,
								@RequestParam(required = false) Integer offset) {
		session.setAttribute("firNav", "数据字典信息管理");
		List<Data_Dictionary> ddList = dataDicS.getDataDicList(new Data_Dictionary(), ObjectUtils.isEmpty(offset) ? 1 : offset);
		PageInfo info = new PageInfo(ddList);
		for (Data_Dictionary ddItem:
			 ddList) {
			ddItem.setTypeInf(dataTypeS.getDataDicType(ddItem.getTypeInf()));
		}
		req.setAttribute("ddInfList", ddList);

		req.setAttribute("ddTypeList", dataTypeS.getAllDataDicType());

		req.setAttribute("TotalPage", info.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);
		req.setAttribute("SearchMode", false);

		return "dataDicInf/showPage";
	}

	@RequestMapping(value = {"/dataDicPage"}, method = RequestMethod.POST)
	public String getDataDicInfListSearch(HttpSession session,
									  HttpServletRequest req,
									  @RequestBody Data_Dictionary ddPar,
									  @RequestParam(required = false) Integer offset) {

		List<Data_Dictionary> ddList = dataDicS.getDataDicList(
						ObjectUtils.isEmpty(ddPar) ? new Data_Dictionary() : ddPar,
						ObjectUtils.isEmpty(offset) ? 1 : offset);

		PageInfo info = new PageInfo(ddList);
		req.setAttribute("ddInfList",ddList);

		req.setAttribute("TotalPage", info.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);
		req.setAttribute("SearchMode", true);

		return "dataDicInf/showPage::data_refresh";
	}

	@RequestMapping("/addDataDic")
	public String toAddPage(HttpSession session,
							HttpServletRequest req) {
		session.setAttribute("firNav", "数据字典信息管理");
		session.setAttribute("SecNav", "添加数据字典信息");

		req.setAttribute("ddTypeList", dataTypeS.getAllDataDicType());

		req.setAttribute("Mode", "Add");
		return "dataDicInf/opDataDicInf";
	}

	@RequestMapping("/ModifyDataDic")
	public String toModifyPage(HttpSession session,
							   HttpServletRequest req,
							   @RequestParam Integer ID) {
		session.setAttribute("SecNav", "修改数据字典信息");

		req.setAttribute("ddTypeList", dataTypeS.getAllDataDicType());
		Data_Dictionary dd = dataDicS.getByID(ID);
		dd.setTypeInf(dataTypeS.getDataDicType(dd.getTypeInf()));
		req.setAttribute("ddInf", dd);

		req.setAttribute("Mode", "Modify");

		return "dataDicInf/opDataDicInf";
	}

	@RequestMapping(value = "/addDataDicInfo")
	@ResponseBody
	public String addDataDicInfo(@RequestBody Data_Dictionary ddPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ddPar))
			obj.put("code", 405);

		if (!dataDicS.addDataDic(ddPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping(value = "/ModifyDataDicInfo")
	@ResponseBody
	public String modDataDicInfo(@RequestBody Data_Dictionary ddPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ddPar))
			obj.put("code", 405);

		if (!dataDicS.modifyDataDic(ddPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping(value = "/DeleteDataDic")
	@ResponseBody
	public String delDataDicInfo(HttpServletRequest req,
							 @RequestParam String ID){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ID))
			obj.put("code", 405);

		if (!dataDicS.delDataDic(Integer.parseInt(ID)))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}
}
