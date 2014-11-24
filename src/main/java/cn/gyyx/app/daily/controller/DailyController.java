package cn.gyyx.app.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gyyx.framework.controller.BaseController;

/**
 * 
 * 日报Controller类
 * 
 * @author bjkandy
 * @Time 2014-11-11 18:44:23
 */

@Controller
@RequestMapping("daily")
public class DailyController extends BaseController {
	private Logger logger = Logger.getLogger(DailyController.class);

	/**
	 * 日报索引页
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author bjkandy
	 * @Time 2014-11-11 18:44:25
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,Model model){
		
		return "/daily/dailyIndex";
	}
}
