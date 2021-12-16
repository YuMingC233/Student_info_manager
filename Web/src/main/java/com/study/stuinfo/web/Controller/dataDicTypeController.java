package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.study.stuinfo.entity.Data_Dictionary_Type;
import com.study.stuinfo.service.DataDicTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class dataDicTypeController {
	@Resource
	DataDicTypeService ddtS;


	@RequestMapping("/DicTypePage")
	public String getDicTypeInfList(HttpSession session,
								  HttpServletRequest req,
								  @RequestParam(required = false) Integer offset){
		session.setAttribute("firNav", "字典类别信息管理");
		session.setAttribute("SecNav", "数据字典类别维护");
		List<Data_Dictionary_Type> ddtList = ddtS.getDataDicTypeList((ObjectUtils.isEmpty(offset) ? 1 : offset));
		PageInfo pageInfo = new PageInfo(ddtList);

		req.setAttribute("dicTypeInfList", ddtList);
		req.setAttribute("TotalPage", pageInfo.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);

		return "dicTypeInf/showPage";
	}

	@RequestMapping("/addDicType")
	public String toAddDicTypePage(HttpSession session
			,HttpServletRequest req){
		session.setAttribute("SecNav", "字典类别添加");

		req.setAttribute("Mode", "Add");

		return "dicTypeInf/opDicTypeInf";
	}

	@RequestMapping("/addDicTypeInfo")
	@ResponseBody
	public String AddDicTypeInf(HttpSession session,
							  HttpServletRequest req,
							  @RequestBody Data_Dictionary_Type ddtPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ddtPar))
			obj.put("code", 405);

		if (!ddtS.addDataDicType(ddtPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/ModifyDicType")
	public String toModifyDicTypePage(HttpSession session,
									HttpServletRequest req,
									@RequestParam Integer TypeID){
		session.setAttribute("SecNav", "字典类别修改");

		Data_Dictionary_Type ddt = new Data_Dictionary_Type();
		ddt.setTypeID(TypeID);

		req.setAttribute("Mode", "Modify");
		req.setAttribute("ddtInf", ddtS.getDataDicType(ddt));

		return "dicTypeInf/opDicTypeInf";
	}

	@RequestMapping("/modDicTypeInfo")
	@ResponseBody
	public String ModifyDicTypeInf( HttpSession session,
								  HttpServletRequest req,
								  @RequestBody Data_Dictionary_Type ddtPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ddtPar))
			obj.put("code", 405);

		if (!ddtS.updDataDicType(ddtPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/DeleteDicType")
	@ResponseBody
	public String DeleteDicTypeInf(HttpSession session,
								 HttpServletRequest req,
								 @RequestParam String TypeID){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(TypeID))
			obj.put("code", 405);

		if (!ddtS.delDataDicType(Integer.parseInt(TypeID)))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}
}
