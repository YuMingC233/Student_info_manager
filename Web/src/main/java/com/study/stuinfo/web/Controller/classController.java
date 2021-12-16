package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.study.stuinfo.entity.Class;
import com.study.stuinfo.service.ClassService;
import com.study.stuinfo.service.GradeService;
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
public class classController {
	@Resource
	ClassService clasS;
	@Resource
	GradeService gradS;

	@RequestMapping("/classPage")
	public String getClassInfList(HttpSession session,
								  HttpServletRequest req,
								  @RequestParam(required = false) Integer offset){
		session.setAttribute("firNav", "班级信息管理");
		List<Class> cList = clasS.getClassList(ObjectUtils.isEmpty(offset) ? 1 : offset);
		PageInfo pageInfo = new PageInfo(cList);

		req.setAttribute("classInfList", cList);
		req.setAttribute("TotalPage", pageInfo.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);

		return "classInf/showPage";
	}

	@RequestMapping("/addClass")
	public String toAddClassPage(HttpSession session
								,HttpServletRequest req){
		session.setAttribute("SecNav", "班级添加");

		req.setAttribute("Mode", "Add");
		req.setAttribute("GradeList", gradS.getAllGrade());

	    return "classInf/opClassInf";
	}

	@RequestMapping("/addClassInfo")
	@ResponseBody
	public String AddClassInf(HttpSession session,
							  HttpServletRequest req,
						      @RequestBody Class clPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(clPar))
			obj.put("code", 405);

		if (!clasS.addClass(clPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/ModifyClass")
	public String toModifyClassPage(HttpSession session,
									HttpServletRequest req,
									@RequestParam Integer classID){
		session.setAttribute("SecNav", "班级修改");

		Class c = new Class();
		c.setClassID(classID);
		Class param = clasS.getClassByID(c);
		param.setGradeInf(gradS.getGrade(param.getGradeInf().getGradeID()));

		req.setAttribute("Mode", "Modify");
		req.setAttribute("GradeList", gradS.getAllGrade());
		req.setAttribute("clInf", param);

		return "classInf/opClassInf";
	}

	@RequestMapping("/modClassInfo")
	@ResponseBody
	public String ModifyClassInf( HttpSession session,
								  HttpServletRequest req,
								  @RequestBody Class clPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(clPar))
			obj.put("code", 405);

		if (!clasS.modifyClass(clPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/DeleteClass")
	@ResponseBody
	public String DeleteClassInf(HttpSession session,
								 HttpServletRequest req,
								 @RequestParam String ClassID){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(ClassID))
			obj.put("code", 405);

		Class c = new Class();
		c.setClassID(Integer.parseInt(ClassID));

		if (!clasS.delClass(c))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}
}
