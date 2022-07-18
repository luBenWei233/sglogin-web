package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
* notice表实体类
* @author 帅哥
* @data 2022-06-21 05:15:22
 */
@TableName("notice")
public class Notice implements Serializable {

private static final long serialVersionUID = 1L;

	// 公告内容
	@TableField(value = "content")
	private String content;
	// 创建时间（如果没有定时发布，则显示为发布时间）
	@TableField(value = "createdate")
	private Date createdate;
	// 创建人
	@TableField(value = "createplayerid")
	private String createplayerid;
	// 公告文件
	@TableField(value = "fileid")
	private String fileid;
	// 公告表主键
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 是否置顶：0、否，1、是
	@TableField(value = "iftop")
	private Integer iftop;
	// 定时发部（并向玩家显示为创建时间）
	@TableField(value = "timing")
	private Date timing;
	// 公告标题
	@TableField(value = "title")
	private String title;
	// 修改时间
	@TableField(value = "updatedate")
	private Date updatedate;
	// 修改人
	@TableField(value = "updateplayerid")
	private String updateplayerid;
	// 是否显示：0、隐藏，1、显示
	@TableField(value = "valid")
	private Integer valid;
	@TableField(exist = false)
	private String filepath;
	@TableField(exist = false)
	private String createplayername;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content=content;
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

	public Date getTiming() {
		return timing;
	}
	public void setTiming(Date timing) {
		this.timing=timing;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getCreateplayername() {
		return createplayername;
	}
	public void setCreateplayername(String createplayername) {
		this.createplayername = createplayername;
	}
}
