package com.myask.domain;

import java.sql.Date;

public class AskVO {
	private long ask_code;
	private String parent_id;
	private String ask;
	private String reply;
	private Date reg_date;
	private Date reply_date;

	public long getAsk_code() {
		return ask_code;
	}

	public void setAsk_code(long ask_code) {
		this.ask_code = ask_code;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
}
