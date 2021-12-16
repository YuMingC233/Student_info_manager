package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.study.stuinfo.entity.Student;
import com.study.stuinfo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class studentController {

	@Resource
	StudentService stuS;
	@Resource
	GradeService gradeS;
	@Resource
	ClassService classS;
	@Resource
	DataDicService dataDicS;

	@RequestMapping(value = {"/studentPage"}, method = RequestMethod.GET)
	public String getStuInfList(HttpSession session,
								HttpServletRequest req,
								@RequestParam(required = false) Integer offset) {
		session.setAttribute("firNav", "学生信息管理");
		List<Student> stuList = stuS.getAllList(new Student(), ObjectUtils.isEmpty(offset) ? 1 : offset);
		PageInfo info = new PageInfo(stuList);
		req.setAttribute("stuInfList", stuList);

		req.setAttribute("SexList", dataDicS.getByTypeID(1));
		req.setAttribute("NationList", dataDicS.getByTypeID(3));
		req.setAttribute("GradeList", gradeS.getAllGrade());
		req.setAttribute("ClassList", classS.getAllClass());

		req.setAttribute("TotalPage", info.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);
		req.setAttribute("SearchMode", false);

		return "student/showPage";
	}

	@RequestMapping(value = {"/studentPage"}, method = RequestMethod.POST)
	public String getStuInfListSearch(HttpSession session,
									  HttpServletRequest req,
									  @RequestBody Student stuPar,
									  @RequestParam(required = false) Integer offset) {

		List<Student> stuList = stuS.getAllList(ObjectUtils.isEmpty(stuPar) ? new Student() : stuPar,
				ObjectUtils.isEmpty(offset) ? 1 : offset);
		PageInfo info = new PageInfo(stuList);
		req.setAttribute("stuInfList",stuList);

		req.setAttribute("TotalPage", info.getPages());
		req.setAttribute("offset", ObjectUtils.isEmpty(offset) ? 1 : offset);
		req.setAttribute("SearchMode", true);

		return "student/showPage::data_refresh";
	}

	@RequestMapping("/addStu")
	public String toAddPage(HttpSession session,
							HttpServletRequest req) {
		session.setAttribute("firNav", "学生信息管理");
		session.setAttribute("SecNav", "添加学生信息");

		req.setAttribute("SexList", dataDicS.getByTypeID(1));
		req.setAttribute("NationList", dataDicS.getByTypeID(3));
		req.setAttribute("ClassList", classS.getAllClass());
		req.setAttribute("PAList", dataDicS.getByTypeID(2));
		req.setAttribute("Mode", "Add");
		return "student/opStudentInf";
	}

	@RequestMapping("/DetailStu")
	public String toDetailPage(HttpSession session,
							   HttpServletRequest req,
							   @RequestParam String StuNo) {
		session.setAttribute("SecNav", "详细学生信息");

		req.setAttribute("Mode", "Detail");
		req.setAttribute("stuInf", stuS.getStudent(StuNo));

		return "student/opStudentInf";
	}

	@RequestMapping("/ModifyStu")
	public String toModifyPage(HttpSession session,
							   HttpServletRequest req,
							   @RequestParam String StuNo) {
		session.setAttribute("SecNav", "修改学生信息");

		req.setAttribute("SexList", dataDicS.getByTypeID(1));
		req.setAttribute("NationList", dataDicS.getByTypeID(3));
		req.setAttribute("ClassList", classS.getAllClass());
		req.setAttribute("PAList", dataDicS.getByTypeID(2));

		req.setAttribute("stuInf", stuS.getStudent(StuNo));

		req.setAttribute("Mode", "Modify");

		return "student/opStudentInf";
	}

	@RequestMapping(value = "/addStudentInfo")
	@ResponseBody
	public String addStuInfo(@RequestBody Student stuPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(stuPar))
			obj.put("code", 405);

		if (!stuS.addStudent(stuPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
 		return obj.toString();
	}

	@RequestMapping(value = "/ModifyStudentInfo")
	@ResponseBody
	public String modStuInfo(@RequestBody Student stuPar){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(stuPar))
			obj.put("code", 405);

		if (!stuS.modifyStudent(stuPar))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}

	@RequestMapping(value = "/DeleteStu")
	@ResponseBody
	public String delStuInfo(HttpServletRequest req,
							 @RequestParam String No){
		JSONObject obj = new JSONObject();
		if (ObjectUtils.isEmpty(No))
			obj.put("code", 405);

		if (!stuS.delStudent(No))
			obj.put("code", 504);
		else
			obj.put("code", 200);
		return obj.toString();
	}
}
