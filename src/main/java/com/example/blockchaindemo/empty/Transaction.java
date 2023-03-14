package com.example.blockchaindemo.empty;

import lombok.Data;

/**
 * 业务数据模型
 * 
 * @author Jared Jia
 *
 */

@Data
public class Transaction {

	private static final long serialVersionUID = 1L;
	//唯一标识
	private String id;
	//业务数据
	private String businessInfo;
}
