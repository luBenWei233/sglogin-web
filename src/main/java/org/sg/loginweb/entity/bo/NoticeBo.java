package org.sg.loginweb.entity.bo;

import java.io.Serializable;
import org.sg.loginweb.util.Condition;

/**
* noticeBo
* @author 帅哥
* @data 2022-07-07 20:34:22
*/

public class NoticeBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 公告内容
	private String content;
	// 创建时间（如果没有定时发布，则显示为发布时间）
	private String createdate;
	// 创建人
	private String createplayerid;
	// 公告文件
	private String fileid;
	// 公告表主键
	private String id;
	// 是否置顶：0、否，1、是
	private Integer iftop;
	// 定时发部（并向玩家显示为创建时间）
	private String timing;
	private String timing1;
	private String timing1_sql;
	private String timing2;
	private String timing2_sql;
	// 公告标题
	private String title;
	// 修改时间
	private String updatedate;
	// 修改人
	private String updateplayerid;
	// 是否显示：0、隐藏，1、显示
	private Integer valid;
	private Integer adminif;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content=content;
	}

	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate=createdate;
	}

	public String getCreateplayerid() {
		return createplayerid;
	}
	public void setCreateplayerid(String createplayerid) {
		this.createplayerid=createplayerid;
	}

	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid=fileid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public Integer getIftop() {
		return iftop;
	}
	public void setIftop(Integer iftop) {
		this.iftop=iftop;
	}

	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing=timing;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}

	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
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
	public String getTiming1() {
		return timing1;
	}
	public void setTiming1(String timing1) {
		this.timing1 = timing1;
	}
	public String getTiming1_sql() {
		return timing1_sql;
	}
	public void setTiming1_sql(String timing1_sql) {
		this.timing1_sql = timing1_sql;
	}
	public String getTiming2() {
		return timing2;
	}
	public void setTiming2(String timing2) {
		this.timing2 = timing2;
	}
	public String getTiming2_sql() {
		return timing2_sql;
	}
	public void setTiming2_sql(String timing2_sql) {
		this.timing2_sql = timing2_sql;
	}
	public Integer getAdminif() {
		return adminif;
	}
	public void setAdminif(Integer adminif) {
		this.adminif = adminif;
	}
}
