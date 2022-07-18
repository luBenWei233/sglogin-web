package org.sg.loginweb.entity.bo;

import java.io.Serializable;

/**
* course_subBo 教程，数据接收
* @author 帅哥
* @data 2022-06-21 05:16:30
 */

public class CourseSubBo implements Serializable {

private static final long serialVersionUID = 1L;

	// 内容
	private String content;
	// 教程子集主键
	private String id;
	// 顺序
	private Integer sort;
	// 内容类型：1、文本，2、图片，3、html
	private Integer type;
	// 父ID
	private String courseid;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content=content;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort=sort;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type=type;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
}
