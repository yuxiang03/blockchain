package com.example.blockchaindemo.empty;

import lombok.Data;

/**
 * p2p通讯消息
 *
 * @author Jared Jia
 * 
 */
@Data
public class Message {
	
	private static final long serialVersionUID = 1L;
	//消息类型
	private int type;
	//消息内容
	private String data;
	public Message() {}

	public Message(int type) {
		this.type = type;
	}

	public Message(int type, String data) {
		this.type = type;
		this.data = data;
	}
}
