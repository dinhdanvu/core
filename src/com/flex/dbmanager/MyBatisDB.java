package com.flex.dbmanager;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisDB {
	public SqlSessionFactory mySqlMap;

	public MyBatisDB() {
		try {
			InputStream configReader = null;
			try {
				configReader = Resources.getResourceAsStream("com/flex/resource/myBitasConfig.xml");
			} catch (Exception ex) {
			}
			try {
				// Nếu sqlMap lỗi thì kiểm tra lại file cấu hình
				// sqlmapconfig.xml
				// và các file map entity bảo đảm tên entity, class đúng hợp lệ
				// các funtion tên phải khác nhau và duy nhất trong tất cả các
				// entity
				mySqlMap = new SqlSessionFactoryBuilder().build(configReader);
			} catch (Exception ex1) {
			}
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	public String getQuery(String s,Object o)
	{
		String rs = "";
		Configuration configuration = mySqlMap.getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(s); 
		BoundSql boundSql = ms.getBoundSql(o);
		rs = boundSql.getSql();
		return rs;
	}
	public List<Object> selectAll(String s) throws SQLException {

		// Primary key return by Insert command
		SqlSession session = mySqlMap.openSession();
		try {
			// Ask SqlMap to do insert task
			return session.selectList(s);
		} catch (Exception x) {

		} finally {
			session.close();
		}
		// Return the primary key
		return null;
	}
	public void selectExecute(String code,Object s) throws SQLException {

		// Primary key return by Insert command
		SqlSession session = mySqlMap.openSession();
		try {
			// Ask SqlMap to do insert task
			List<Object> o = session.selectList(code,s);
			s.toString();
		} catch (Exception x) {

		} finally {
			session.close();
		}
	}
	public void insertExecute(String code,Object s) throws SQLException {

		// Primary key return by Insert command
		SqlSession session = mySqlMap.openSession();
		try {
			// Ask SqlMap to do insert task
			String sss = getQuery(code, s);
			session.insert(code,s);
			System.out.println("run end");
		} catch (Exception x) {
			System.out.println("ex=>"+x.getMessage());
		} finally {
			session.close();
		}
	}
}
