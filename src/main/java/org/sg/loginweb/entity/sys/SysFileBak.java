package org.sg.loginweb.entity.sys;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* sys_file_bak表实体类
* @author 帅哥
* @data 2022-06-21 05:21:51
 */
@TableName("sys_file_bak")
public class SysFileBak implements Serializable {

private static final long serialVersionUID = 1L;

	// 文件主键
	@TableId(value="fileid",type=IdType.INPUT)
	private String fileid;
	// 超过300自动截断，原始文件名
	@TableField(value = "filename")
	private String filename;
	// 其它表与本字段关联
	@TableField(value="keyid")
	private String keyid;
	// 保存在服务器上的文件路径（相对路径）
	@TableField(value = "path")
	private String path;
	// 修改后的文件名（fileid+后缀）
	@TableField(value = "realname")
	private String realname;
	// 服务器代号，为了方便文件迁移
	@TableField(value = "serverid")
	private String serverid;
	// 互联网访问地址（定制或你自己开发时可以用存储桶）
	@TableField(value = "url")
	private String url;

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
}
