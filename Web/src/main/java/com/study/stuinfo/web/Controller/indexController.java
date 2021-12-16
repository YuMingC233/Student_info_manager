package com.study.stuinfo.web.Controller;

import com.study.stuinfo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class indexController {

	@RequestMapping("/index")
	public String indexPage(HttpSession session) {
		session.setAttribute("firNav", null);
		session.setAttribute("SecNav", null);
		return "index";
	}

}
