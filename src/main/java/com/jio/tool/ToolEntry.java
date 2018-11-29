package com.jio.tool;

import java.util.List;

import com.jio.tool.cassandra.ScyllaDao;
import com.jio.tool.mysql.MysqlDao;
import com.jio.tool.mysql.entity.MessageEntity;

/**
 * Hello world!
 *
 */
public class ToolEntry
{
	public static void main(String[] args)
	{
		int currentTableNumber = 1;

		int msgCount = MysqlDao.getMaxId(currentTableNumber);
		int id = 0;
		while (id < msgCount)
		{
			List<MessageEntity> list = MysqlDao.getMessageList(currentTableNumber, id, 1000);
			if (null != list && !list.isEmpty())
			{
				for (MessageEntity msg : list)
				{
					if (null == msg.getMsgContent())
					{
						continue;
					}
					String scyllaMsg = ScyllaDao.findMessage(msg.getSessionKey(), msg.getMsgIndex());
					if (bytes2String(msg.getMsgContent()).equals(scyllaMsg))
						continue;
					else
					{
						// TODO
						System.out.println("not matched...");
					}
				}

			}
			id += 1000;
			System.out.println("Alrady proccess records: " + id + "/" + msgCount);
		}
	}

	private static String bytes2String(byte[] value)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : value)
		{
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
