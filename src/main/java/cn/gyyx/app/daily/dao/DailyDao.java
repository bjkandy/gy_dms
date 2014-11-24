package cn.gyyx.app.daily.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.gyyx.framework.db.BaseDao;

/**
 * 
 * 
 * @author bjkandy
 *
 */

@Repository("dailyDao")
public class DailyDao extends BaseDao {
	
	public List<?> getDailyListByUserId(String userId){
		
		return null;
	}

}
