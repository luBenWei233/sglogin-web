package org.sg.loginweb.entity.vo;

import java.io.Serializable;

/**
* sys_fileVo
* @author 帅哥
* @data 2022-07-07 09:09:27
 */

public class SysFileVo implements Serializable {

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
	// 创建时间
	private String createdate;
	// 创建人
	private String createplayerid;
	private String createplayername;

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
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateplayerid() {
		return createplayerid;
	}
	public void setCreateplayerid(String createplayerid) {
		this.createplayerid = createplayerid;
	}
	public String getCreateplayername() {
		return createplayername;
	}
	public void setCreateplayername(String createplayername) {
		this.createplayername = createplayername;
	}
}
