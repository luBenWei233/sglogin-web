package org.sg.loginweb.entity.bo;

import java.io.Serializable;
import org.sg.loginweb.util.Condition;

/**
* sys_databookBo
* @author 帅哥
* @data 2022-06-25 11:21:07
 */
public class SysDatabookBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 创建时间
	private String createdate;
	// 创建人id
	private String createplayerid;
	// 字典类型
	private Integer groupid;
	// 字典表主键
	private Integer id;
	// 与本表主键关联，如不是子级，请不要有值！
	private Integer parentid;
	// 更新时间
	private String updatedate;
	// 更新人
	private String updateplayerid;
	// 0:隐藏、1:使用
	private Integer valid;
	// 字典值
	private String value;

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

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
}
