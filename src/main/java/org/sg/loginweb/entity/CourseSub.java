package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* course_sub表实体类
* @author 帅哥
* @data 2022-06-21 05:16:30
 */
@TableName("course_sub")
public class CourseSub implements Serializable {

private static final long serialVersionUID = 1L;

	// 内容
	@TableField(value = "content")
	private String content;
	// 教程子集主键
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 顺序
	@TableField(value = "sort")
	private Integer sort;
	// 内容类型：1、文本，2、图片，3、html
	@TableField(value = "type")
	private Integer type;
	// 父ID
	@TableField(value = "courseid")
	private String courseid;
	// 图片路径
	@TableField(exist = false)
	private String imgpath;

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
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
}
