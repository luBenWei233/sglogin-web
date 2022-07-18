package org.sg.loginweb.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* courseVo教程展示层
* @author 帅哥
* @data 2022-07-01 19:58:43
 */

public class CourseVo implements Serializable {

private static final long serialVersionUID = 1L;

	// 教程类型
	private Integer coursetype;
	private String coursetypename;
	private String coursecontent;
	// 发布时间
	private Date createdate;
	// 发布人
	private String createplayerid;
	private String createplayername;
	// 教程主键
	private String id;
	// 教程标题
	private String title;
	// 更新时间
	private Date updatedate;
	// 更新人
	private String updateplayerid;
	// 是否发布：0、否，1、是
	private Integer valid;
	private String validname;
	// 发布时间
	private String releasedate;
	// 发布时间
	private String releasedateT;
	private Integer defaultif;
	
	private List<CourseSubVo> courseSubVos;

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
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	public String getReleasedateT() {
		return releasedateT;
	}
	public void setReleasedateT(String releasedateT) {
		this.releasedateT = releasedateT;
	}
	public String getCreateplayername() {
		return createplayername;
	}
	public void setCreateplayername(String createplayername) {
		this.createplayername = createplayername;
	}
	public List<CourseSubVo> getCourseSubVos() {
		return courseSubVos;
	}
	public void setCourseSubVos(List<CourseSubVo> courseSubVos) {
		this.courseSubVos = courseSubVos;
	}
	public String getCoursetypename() {
		return coursetypename;
	}
	public void setCoursetypename(String coursetypename) {
		this.coursetypename = coursetypename;
	}
	public String getValidname() {
		return validname;
	}
	public void setValidname(String validname) {
		this.validname = validname;
	}
	public String getCoursecontent() {
		return coursecontent;
	}
	public void setCoursecontent(String coursecontent) {
		this.coursecontent = coursecontent;
	}
	public Integer getDefaultif() {
		return defaultif;
	}
	public void setDefaultif(Integer defaultif) {
		this.defaultif = defaultif;
	}
}
