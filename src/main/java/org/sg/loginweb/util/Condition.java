package org.sg.loginweb.util;

import com.baomidou.mybatisplus.annotation.TableField;

public class Condition {
	//请求页数
	@TableField(exist = false)
	private Integer resPage=1;
	//请求页大小
	@TableField(exist = false)
	private Integer resPageSize=15;
	//是哪种类型的数据库
	@TableField(exist = false)
	private String dataBaseType=MyUtil.getDataBaseType();
	//时区
	@TableField(exist = false)
	private int gmt=MyUtil.getGmt();
	public Integer getResPage() {
		return resPage;
	}
	public void setResPage(Integer resPage) {
		this.resPage = resPage;
	}
	public Integer getResPageSize() {
		return resPageSize;
	}
	public void setResPageSize(Integer resPageSize) {
		this.resPageSize = resPageSize;
	}
	public String getDataBaseType() {
		return dataBaseType;
	}
	public int getGmt() {
		return gmt;
	}
	public void setGmt(int gmt) {
		this.gmt = gmt;
	}
}
