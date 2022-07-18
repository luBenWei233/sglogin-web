package org.sg.loginweb.entity.bo;

import java.io.Serializable;

import org.sg.loginweb.util.Condition;

/**
* sys_group表实Bo
* @author 帅哥
* @data 2022-06-24 10:35
 */
public class SysGroupBo extends Condition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// id
	private Integer id;

	// 字典类型值
	private String value;
	
	// 排序
	private Integer sort;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
