package cn.gyyx.framework.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gyyx.framework.controller.BaseController;

@Controller
@RequestMapping("securityLogin")
public class LoginController extends BaseController {
	private Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		
		return null;
	}

}
