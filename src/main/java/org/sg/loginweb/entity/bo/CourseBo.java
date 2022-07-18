package org.sg.loginweb.entity.bo;

import java.io.Serializable;
import java.util.Date;

import org.sg.loginweb.util.Condition;

/**
* course接收类
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

public class CourseBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 教程类型
	private Integer coursetype;
	// 发布时间
	private Date createdate;
	// 发布人
	private String createplayerid;
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
	private Integer defaultif;
	// 发布日期
	private String releasedate;
	private String releasedate1;
	private String releasedate1_sql;
	private String releasedate2;
	private String releasedate2_sql;

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
	public String getReleasedate1() {
		return releasedate1;
	}
	public void setReleasedate1(String releasedate1) {
		this.releasedate1 = releasedate1;
	}
	public String getReleasedate1_sql() {
		return releasedate1_sql;
	}
	public void setReleasedate1_sql(String releasedate1_sql) {
		this.releasedate1_sql = releasedate1_sql;
	}
	public String getReleasedate2() {
		return releasedate2;
	}
	public void setReleasedate2(String releasedate2) {
		this.releasedate2 = releasedate2;
	}
	public String getReleasedate2_sql() {
		return releasedate2_sql;
	}
	public void setReleasedate2_sql(String releasedate2_sql) {
		this.releasedate2_sql = releasedate2_sql;
	}
	public Integer getDefaultif() {
		return defaultif;
	}
	public void setDefaultif(Integer defaultif) {
		this.defaultif = defaultif;
	}
}
