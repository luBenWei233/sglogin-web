package org.sg.loginweb.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 统一返回结果
 * @author 帅哥
 * 2021-06-08 23:10
 * @param <T>
 */
public class Result {
	
	private int code=200;
	private String message="success";
	//总页数
	private Long totalPage;
	//总条数
	private Long totalNumber;
	//当前页
	private Long currentPage;
	//每页条数
	private Long currentNumber;
	
	public Result(int code) {
		this.code=code;
	}
	public Result(int code,Object data) {
		this.code=code;
		this.data=data;
	}
	
	public Result(IPage<?> page) {
		this.totalPage=page.getPages();
		this.totalNumber=page.getTotal();
		this.currentPage=page.getCurrent();
		this.currentNumber=page.getSize();
		this.data=page.getRecords();
	}
	
	public Result(IPage<?> page,Object obj) {
		this.totalPage=page.getPages();
		this.totalNumber=page.getTotal();
		this.currentPage=page.getCurrent();
		this.currentNumber=page.getSize();
		this.data=obj;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Long currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	private Object data;
	
}
