package org.sg.loginweb.entity.sys;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import org.sg.loginweb.entity.Course;

/**
* sys_databook表实体类
* @author 帅哥
* @data 2022-06-21 05:21:07
 */
@TableName("sys_databook")
public class SysDatabook implements Serializable {

private static final long serialVersionUID = 1L;

	// 创建时间
	@TableField(value = "createdate")
	private Date createdate;
	// 创建人id
	@TableField(value = "createplayerid")
	private String createplayerid;
	// 字典类型
	@TableField(value = "groupid")
	private Integer groupid;
	// 字典表主键
	@TableId(value="id",type=IdType.AUTO)
	private Integer id;
	// 与本表主键关联，如不是子级，请不要有值！
	@TableField(value = "parentid")
	private Integer parentid;
	// 更新时间
	@TableField(value = "updatedate")
	private Date updatedate;
	// 更新人
	@TableField(value = "updateplayerid")
	private String updateplayerid;
	// 0:隐藏、1:使用
	@TableField(value = "valid")
	private Integer valid;
	// 字典值
	@TableField(value = "value")
	private String value;
	
	//创建人名
	@TableField(exist = false)
	private String createname;
	//字典类型id
	@TableField(exist = false)
	private Integer sysgroupid;
	//字典类型名
	@TableField(exist = false)
	private String sysgroupvalue;
	@TableField(exist = false)
	private List<Course> courses;

	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate=createdate;
	}

	public String getCreateplayerid() {
		return createplayerid;
	}
	public void setCreateplayerid(String createplayerid) {
		this.createplayerid=createplayerid;
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

	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate=updatedate;
	}

	public String getUpdateplayerid() {
		return updateplayerid;
	}
	public void setUpdateplayerid(String updateplayerid) {
		this.updateplayerid=updateplayerid;
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
	public String getCreatename() {
		return createname;
	}
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
