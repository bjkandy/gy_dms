package cn.gyyx.app.daily.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;






import cn.gyyx.app.daily.business.DailyBusiness;
import cn.gyyx.app.daily.model.Daily;
import cn.gyyx.app.daily.model.Work;
import cn.gyyx.framework.controller.BaseController;
import cn.gyyx.framework.util.JSONUtils;
import cn.gyyx.framework.util.time.DateFormatUtils;

/**
 * 
 * 日报Controller类
 * 
 * @author bjkandy
 * @Time 2014-11-11 18:44:23
 */

@Controller
@RequestMapping("/daily")
public class DailyController extends BaseController {
	private Logger logger = Logger.getLogger(DailyController.class);

	@Autowired
	private DailyBusiness dailyBusinessImpl;
	
	/**
	 * 日报索引页
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author bjkandy
	 * @Time 2014-11-11 18:44:25
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model){
		return "/daily/dailyIndex";
	}
	
	@RequestMapping("/addNewDaily")
	public String addNewDaily(String jsonData){
		
		Daily[] daily = JSONUtils.JSONString2Object(jsonData,null, Daily[].class);
		
		List<Work> works = daily[0].getWorks();
		Date date = new Date();
		daily[0].setEmployeeId(1);//从session里取ID
		daily[0].setEmployeeName("里斯");//从session里取
		daily[0].setDailyCreateTime(date);//日报创建日期(具体到时分秒)
		daily[0].setDailyDate(DateFormatUtils.format(date, "yyyy-MM-dd"));
		daily[0].setDailyStatus(0);
		
		dailyBusinessImpl.addDaily(daily[0]);
		
		//给每一条工作记录设置dailyId
		for(Work work : works){
			work.setDailyId(daily[0].getDailyId());
		}
		
		dailyBusinessImpl.addWork(works);
		
		return "/daily/dailyIndex";
	}
	
	/**
	 * 
	 * @param daily
	 * @param model
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("/dailyList/{currentPage}")
	public String getDailyByCondition(Daily daily,Model model,@PathVariable("currentPage") int currentPage){
		logger.info(daily);
		logger.info(currentPage);
		List<Daily> dailies = dailyBusinessImpl.getDailyByCondition(daily, currentPage);
		model.addAttribute("dailies", dailies);
		model.addAttribute("daily", daily);
		logger.info(dailies);
		return "/daily/dailyList";
	}
	@RequestMapping("/deleteDaily")
	public String deleteDaily(int dailyId){
		dailyBusinessImpl.deleteDailyById(dailyId);
		return "/daily/dailyList";
	}
	@RequestMapping("/dailyDetail/{dailyId}")
	public String dailyDetail(@PathVariable("dailyId")int dailyId){
		logger.info(dailyId);
		return "/daily/dailyDetail";
	}
}
