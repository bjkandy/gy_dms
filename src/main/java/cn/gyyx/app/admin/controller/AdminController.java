package cn.gyyx.app.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gyyx.framework.controller.BaseController;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,Model model){
		return "app/admin/layout";
	}

}
