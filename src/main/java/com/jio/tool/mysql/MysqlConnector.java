package com.jio.tool.mysql;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jio.tool.mysql.entity.TestToekn;
import com.jio.tool.mysql.mappers.MessageTokenMapper;

public class MysqlConnector
{
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static
	{
		try
		{
			reader = Resources.getResourceAsReader("mybatis_config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSessionFactory.getConfiguration().addMappers("com.jio.tool.mysql.mappers");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession()
	{
		return sqlSessionFactory;
	}

	public static void main(String[] args)
	{
		SqlSession session = sqlSessionFactory.openSession();
		try
		{
			MessageTokenMapper mapper = session.getMapper(MessageTokenMapper.class);
			TestToekn user = mapper.getUserTokenById(1, 1);
			System.out.println("名字：" + user.getId());
			System.out.println("所属部门：" + user.getUserid());
			System.out.println("主页：" + user.getToken());
			System.out.println("maxid : " + mapper.getMaxid(1));
		}
		finally
		{
			session.close();
		}
	}
}