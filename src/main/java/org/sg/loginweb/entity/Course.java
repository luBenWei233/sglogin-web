package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

/**
* course表实体类
* @author 帅哥
* @data 2022-06-21 05:16:43
 */
@TableName("course")
public class Course implements Serializable {

private static final long serialVersionUID = 1L;

	// 教程类型
	@TableField(value = "coursetype")
	private Integer coursetype;
	private String coursetypename;
	// 发布时间
	@TableField(value = "createdate")
	private Date createdate;
	// 发布人
	@TableField(value = "createplayerid")
	private String createplayerid;
	// 教程主键
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 教程标题
	@TableField(value = "title")
	private String title;
	// 更新时间
	@TableField(value = "updatedate")
	private Date updatedate;
	// 更新人
	@TableField(value = "updateplayerid")
	private String updateplayerid;
	// 是否发布：0、否，1、是
	@TableField(value = "valid")
	private Integer valid;
	// 没有ID时的默认教程：0、否，1、是
	@TableField(value = "defaultif")
	private Integer defaultif;
	// 发布时间
	@TableField(value = "releasedate")
	private Date releasedate;
	// 创建的管理员名
	@TableField(exist = false)
	private String createplayername;
	
	@TableField(exist = false)
	private List<CourseSub> courseSubs;

	public Integer getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(Integer coursetype) {
		this.coursetype=coursetype;
	}

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

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
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
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
	public String getCreateplayername() {
		return createplayername;
	}
	public void setCreateplayername(String createplayername) {
		this.createplayername = createplayername;
	}
	public List<CourseSub> getCourseSubs() {
		return courseSubs;
	}
	public void setCourseSubs(List<CourseSub> courseSubs) {
		this.courseSubs = courseSubs;
	}
	public String getCoursetypename() {
		return coursetypename;
	}
	public void setCoursetypename(String coursetypename) {
		this.coursetypename = coursetypename;
	}
	public Integer getDefaultif() {
		return defaultif;
	}
	public void setDefaultif(Integer defaultif) {
		this.defaultif = defaultif;
	}
}
