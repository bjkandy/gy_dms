package cn.gyyx.framework.db;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private SqlSessionTemplate sqlSession2;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public SqlSessionTemplate getSqlSession2() {
		return sqlSession2;
	}
}
