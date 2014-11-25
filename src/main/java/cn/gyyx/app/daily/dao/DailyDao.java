package cn.gyyx.app.daily.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.gyyx.app.daily.model.Daily;
import cn.gyyx.app.daily.model.Work;
import cn.gyyx.framework.db.BaseDao;

/**
 * 
 * 
 * @author bjkandy
 *
 */

@Repository("dailyDao")
public class DailyDao extends BaseDao {
	
	//添加日报
	public void addDaily(Daily daily){
		
		this.getSqlSession().insert("daily.addDaily",daily);
	}
	
	//添加工作内容
	public void addWork(List<Work> works){
		this.getSqlSession().insert("work.addWorks",works);
	}
	
	//根据条件查询日报
	public List<Daily> getDailyByCondition(Map<String, Object> condition){
		return this.getSqlSession().selectList("daily.getDailyByCondition",condition);
	}
	
	//查询所有日报
	public List<Daily> getAllDailies(){
		return this.getSqlSession().selectList("daily.getAllDailies");
	}
	//根据Id删除日报
	public void deleteDailyById(int dailyId) {
		this.getSqlSession().delete("daily.deleteDailyById", dailyId);
		this.getSqlSession().delete("daily.deleteWorksByDailyId", dailyId);
	}

}
