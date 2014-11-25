package cn.gyyx.app.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gyyx.app.admin.business.AdminBusiness;
import cn.gyyx.app.admin.model.Settings;
import cn.gyyx.framework.controller.BaseController;
import cn.gyyx.framework.plugins.StringToDate;

/**
 * 
 * 管理Controller类
 * 
 * @author 席江平
 * @Time 2014-11-20 18:44:23
 */
@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminBusiness adminBusinessImpl;

	/**
	 * 系统管理索引页
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author 席江平
	 * @Time 2014-11-20 18:44:25
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		Settings settings = adminBusinessImpl.select();
		String rtxstarttime = settings.getRtxStarttime().toString();
		rtxstarttime = rtxstarttime.substring(11, 16);
		String rtxlasttime = settings.getRtxLasttime().toString();
		rtxlasttime = rtxlasttime.substring(11, 16);
		String weeklysumtime = settings.getWeeklySumtime().toString();
		weeklysumtime = weeklysumtime.substring(11, 16);
		model.addAttribute("settings", settings);
		model.addAttribute("rtxstarttime", rtxstarttime);
		model.addAttribute("rtxlasttime", rtxlasttime);
		model.addAttribute("weeklysumtime", weeklysumtime);
		return "/admin/layout";
	}

	/*
	 * 系统配置日报时间，周报汇总时间设置方法
	 * 
	 * @author 席江平
	 * 
	 * @Time 2014-11-20 18:44:23
	 */
	@RequestMapping("update")
	public String update(HttpServletRequest request, Settings settings,
			String rtxstarttime, String rtxlasttime, String weeklysumtime,
			Model model) {
		StringToDate stringToDateConverter = new StringToDate();
		settings.setRtxStarttime(stringToDateConverter.convert(rtxstarttime));
		settings.setRtxLasttime(stringToDateConverter.convert(rtxlasttime));
		settings.setWeeklySumtime(stringToDateConverter.convert(weeklysumtime));
		this.adminBusinessImpl.update(settings);
		return "redirect:/admin/index.do";
	}
	/*
	 * 提醒人员设置方法
	 *@author 席江平
	 * 
	 * @Time 2014-11-23 21:44:23
	 * 
	 */
	@RequestMapping("setRemind")
	public String  setRemind() {
		return null;
		
	}

}
