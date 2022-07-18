package org.sg.loginweb.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.sg.loginweb.entity.Course;

/**
* sys_databookvo表实体类
* @author 帅哥
* @data 2022-06-21 05:21:07
*/

public class SysDatabookVo implements Serializable {

private static final long serialVersionUID = 1L;

	// 创建时间
	private Date createdate;
	// 字典类型
	private Integer groupid;
	// 字典表主键
	private Integer id;
	// 与本表主键关联，如不是子级，请不要有值！
	private Integer parentid;
	// 0:隐藏、1:使用
	private Integer valid;
	// 0:隐藏、1:使用（前端展示）
	private String validshow;
	// 字典值
	private String value;
	// 创建人
	private String createname;
	//字典类型id
	private Integer sysgroupid;
	//字典类型名
	private String sysgroupvalue;
	private List<Course> courses;

	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate=createdate;
	}

	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid=groupid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid=parentid;
	}

	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid=valid;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
	public String getCreatename() {
		return createname;
	}
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	public Integer getSysgroupid() {
		return sysgroupid;
	}
	public void setSysgroupid(Integer sysgroupid) {
		this.sysgroupid = sysgroupid;
	}
	public String getSysgroupvalue() {
		return sysgroupvalue;
	}
	public void setSysgroupvalue(String sysgroupvalue) {
		this.sysgroupvalue = sysgroupvalue;
	}
	public String getValidshow() {
		return validshow;
	}
	public void setValidshow(String validshow) {
		this.validshow = validshow;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
