package com.jio.tool.mysql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jio.tool.mysql.entity.MessageEntity;
import com.jio.tool.mysql.mappers.MessageTokenMapper;

public class MysqlDao
{
	public static int getMaxId(int tableNumber)
	{
		SqlSession session = MysqlConnector.getSession().openSession();
		MessageTokenMapper mapper = session.getMapper(MessageTokenMapper.class);

		return mapper.getMaxId(tableNumber);
	}

	public static List<MessageEntity> getMessageList(int tableNumber, int startid, int number)
	{
		List<MessageEntity> list = null;
		SqlSession session = MysqlConnector.getSession().openSession();
		try
		{
			MessageTokenMapper mapper = session.getMapper(MessageTokenMapper.class);

			list = mapper.getMessages(tableNumber, startid, startid + number);
		}
		finally
		{
			session.close();
		}

		return list;
	}
}
