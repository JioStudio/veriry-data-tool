package com.jio.tool.mysql.entity;

import lombok.Data;

@Data
public class MessageEntity
{
	private String sessionKey;
	private int msgIndex;
	private byte[] msgContent;
	private String msg_content;
}
