package com.jio.tool.cassandra;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.jio.tool.mysql.entity.MessageEntity;

/**
 * Scylla DB Operations for Message
 * 
 * @author King.Gao
 *
 */
public class ScyllaDao
{

	public static String findMessage(String sessionKey, int msgIndex)
	{
		CassandraOperations op = ScyllaConnector.instance().getTemplate();

		Query query = Query.query(Criteria.where("session_key").is(sessionKey), Criteria.where("tenant_id").is("TENANT_JIOCHAT"), Criteria.where("msg_index").is(msgIndex));

		MessageEntity msg = op.selectOne(query, MessageEntity.class);
		if (null != msg)
		{
			return msg.getMsg_content();
		}
		else
		{
			return null;
		}
	}
}
