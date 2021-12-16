package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.study.stuinfo.entity.Grade;
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
public class gradeController {
	@Resource
	GradeService foo;

	@RequestMapping("/gradePage")
	public String getGradeInfList(HttpSession session,
								  HttpServletRequest req,
								  @RequestParam(required = false) Integer offset){
		session.setAttribute("firNav", "年级信息管理");
		List<Grade> gList = foo.getGradeList(ObjectUtils.isEmpty(offset) ? 1 : offset);
		PageInfo pageInfo = new PageInfo(gList);

		req.setAttribute("gradeInfList", gList);
		req.setAttribute("TotalPage", pageInfo.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);

		return "gradeInf/showPage";
	}

	@RequestMapping("/addGrade")
	public String toAddgradePage(HttpSession session
								,HttpServletRequest req){
		session.setAttribute("SecNav", "年级添加");

		req.setAttribute("Mode", "Add");

		return "gradeInf/opGradeInf";
	}

	@RequestMapping("/addGradeInfo")
	@ResponseBody
	public String AddGradeInf(HttpSession session,
							  HttpServletRequest req,
							  @RequestBody Grade grPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(grPar))
			obj.put("code", 405);

		if (!foo.addGrade(grPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/ModifyGrade")
	public String toModifyGradePage(HttpSession session,
									HttpServletRequest req,
									@RequestParam Integer GradeID){
		session.setAttribute("SecNav", "年级修改");

		req.setAttribute("Mode", "Modify");
		req.setAttribute("grInf", foo.getGrade(GradeID));

		return "gradeInf/opGradeInf";
	}

	@RequestMapping("/modGradeInfo")
	@ResponseBody
	public String ModifyGradeInf( HttpSession session,
								  HttpServletRequest req,
								  @RequestBody Grade grPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(grPar))
			obj.put("code", 405);

		if (!foo.modifyGrade(grPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping("/DeleteGrade")
	@ResponseBody
	public String DeleteGradeInf(HttpSession session,
								 HttpServletRequest req,
								 @RequestParam String GradeID){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(GradeID))
			obj.put("code", 405);

		Grade gr = new Grade();
		gr.setGradeID(Integer.parseInt(GradeID));

		if (!foo.delGrade(gr))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}
}
