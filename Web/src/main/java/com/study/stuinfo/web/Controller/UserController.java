package com.study.stuinfo.web.Controller;

import com.alibaba.fastjson.JSONObject;
import com.study.stuinfo.entity.User;
import com.study.stuinfo.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
	@Resource
	UserService user;

	@RequestMapping({"/login"})
	public String defaultPage(HttpServletRequest req) {
		req.setAttribute("captErr",false);
		req.setAttribute("logErr",false);
		return "login";
	}

	@RequestMapping("/getCPT")
	public void getCAPTCHA(HttpServletRequest req, HttpServletResponse resp) {
		try {
			CaptchaUtil.out(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("/doLogin")
	public String login(HttpServletRequest req,
						HttpSession session,
						@RequestParam String uName,
						@RequestParam String uPwd,
						@RequestParam String uCaptcha){
		if (uName.isEmpty() || uPwd.isEmpty() || uCaptcha.isEmpty()) {
			return "login";
		}

		User u = new User(null,uName ,uPwd);
		if (!CaptchaUtil.ver(uCaptcha, req)) {
			CaptchaUtil.clear(req);
			req.setAttribute("captErr",true);
			return "login";
		} else if (!user.Login(u)) {
			req.setAttribute("logErr",true);
			return "login";
		}
		session.setAttribute("userInfo", new User(null, uName,null));
		return "index";
	}

	@RequestMapping("/modifyPwd")
	public String toModifyPwd(HttpSession session,
							  HttpServletRequest req,
							  @RequestParam(required = false) Integer offset){
		session.setAttribute("firNav", "修改密码");
		session.setAttribute("SecNav", null);
		return "modifyPassword";
	}

	@RequestMapping("/ModifyPassword")
	@ResponseBody
	public String modifyPwd(@RequestParam("UserName") String UserName,
							@RequestParam("oldPwd") String oldPwd,
							@RequestParam("NewPwd") String NewPwd){
		JSONObject obj = new JSONObject();
		User u = user.getUserInfByName(UserName);
		if (u == null) {
			obj.put("code", 404);
			return obj.toString();
		}

		if (!u.getPassword().equals(oldPwd)){
			obj.put("code", 405);
			return obj.toString();
		}

		u.setPassword(NewPwd);

		if (!user.updPassWord(u))
			obj.put("code", 406);
		else
			obj.put("code", 200);
	    return obj.toString();
	}

	@RequestMapping("/EXIT")
	@ResponseBody
	public String exitSystem(HttpSession session){
		JSONObject obj = new JSONObject();
		session.invalidate();
		obj.put("code", 200);
	    return obj.toString();
	}
}
