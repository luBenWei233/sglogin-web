package org.sg.loginweb.entity.bo;

import java.io.Serializable;

import org.sg.loginweb.util.Condition;

/**
* sys_fileBo 文件接收
* @author 帅哥
* @data 2022-06-21 05:21:27
 */
public class SysFileBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 文件主键
	private String fileid;
	// 超过300自动截断，原始文件名
	private String filename;
	// 其它表与本字段关联
	private String keyid;
	// 保存在服务器上的文件路径（相对路径）
	private String path;
	// 修改后的文件名（fileid+后缀）
	private String realname;
	// 服务器代号，为了方便文件迁移
	private String serverid;
	// 互联网访问地址（定制或你自己开发时可以用存储桶）
	private String url;
	// 管理员ID
	private String createplayerid;
	// 创建时间
	private String createdate;
	private String createdate1;
	private String createdate1_sql;
	private String createdate2;
	private String createdate2_sql;

	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid=fileid;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename=filename;
	}

	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid=keyid;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path=path;
	}

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname=realname;
	}

	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid=serverid;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getCreateplayerid() {
		return createplayerid;
	}
	public void setCreateplayerid(String createplayerid) {
		this.createplayerid = createplayerid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatedate1() {
		return createdate1;
	}
	public void setCreatedate1(String createdate1) {
		this.createdate1 = createdate1;
	}
	public String getCreatedate1_sql() {
		return createdate1_sql;
	}
	public void setCreatedate1_sql(String createdate1_sql) {
		this.createdate1_sql = createdate1_sql;
	}
	public String getCreatedate2() {
		return createdate2;
	}
	public void setCreatedate2(String createdate2) {
		this.createdate2 = createdate2;
	}
	public String getCreatedate2_sql() {
		return createdate2_sql;
	}
	public void setCreatedate2_sql(String createdate2_sql) {
		this.createdate2_sql = createdate2_sql;
	}
}
