package cn.gyyx.framework.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gyyx.app.common.business.EmployeeBusiness;
import cn.gyyx.app.common.model.Employee;
import cn.gyyx.framework.controller.BaseController;
import cn.gyyx.framework.util.Constant;

@Controller
@RequestMapping("securityLogin")
public class LoginController extends BaseController {
	private Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private EmployeeBusiness employeeBusinessImpl;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,String email,String emailPasswd,HttpServletResponse response){
		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(emailPasswd)) {
			this.employeeBusinessImpl.hasEmployee(email, password);
			Employee employee = employeeBusinessImpl.hasEmployee(email,
					emailPasswd);
			
			if(employee!=null){
				String message = "登录成功！！！";
				// 将employee存到session中
				request.getSession().setAttribute(Constant.LOGIN_USER, employee);
				model.addAttribute("message", message);
				model.addAttribute("employee", employee);
				return "/daily/test2";
			}else{
				String message = "登录失败，请重新登录！！！";
				request.getSession().setAttribute("message", message);
				//model.addAttribute("message", message);
				// return "Redirect:/login.jsp";
				return "redirect:/login.jsp";
			}
			

		} else {
			//String message = "";
			//model.addAttribute("message", message);
			// return "Redirect:/login.jsp";
			return "redirect:/login.jsp";
		}
		return null;
	}

}
