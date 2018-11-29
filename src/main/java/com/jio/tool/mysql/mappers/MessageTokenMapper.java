package com.jio.tool.mysql.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jio.tool.mysql.entity.MessageEntity;
import com.jio.tool.mysql.entity.TestToekn;

public interface MessageTokenMapper
{

	@Select("SELECT id, token FROM blink_usertokens_#{arg0} WHERE id=#{arg1}")
	public TestToekn getUserTokenById(int tableNumber, int id);
	
	@Select("SELECT MAX(id) FROM blink_usertokens_#{arg0}")
	public int getMaxid(int tableNumber);

	@Select("SELECT sessionkey, sessionid, message FROM rcs_message_#{arg0} WHERE id>=#{arg1} AND id<=#{arg2}")
	public List<MessageEntity> getMessages(int tableNumber, int start, int end);

	@Select("SELECT MAX(id) FROM rcs_message_#{arg0}")
	public int getMaxId(int tableNumber);
}
