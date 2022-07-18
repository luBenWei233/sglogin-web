package org.sg.loginweb.entity.sys;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import org.sg.loginweb.util.Condition;

/**
* sys_config表实体类
* @author 帅哥
* @data 2022-06-21 05:20:52
 */
@TableName("sys_config")
public class SysConfig extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 0:不可以修改、1:可以修改
	@TableField(value = "change")
	private Integer change;
	// 系统表主键，请勿修改本主键
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 备注说明
	@TableField(value = "remarks")
	private String remarks;
	// 更新时间
	@TableField(value = "updatedate")
	private Date updatedate;
	@TableField(exist = false)
	private String updatedateShow;
	// 更新人员
	@TableField(value = "updateplayerid")
	private String updateplayerid;
	@TableField(exist = false)
	private String updateplayername;
	// 值
	@TableField(value = "value")
	private String value;

	public Integer getChange() {
		return change;
	}
	public void setChange(Integer change) {
		this.change=change;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks=remarks;
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

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
	public String getUpdateplayername() {
		return updateplayername;
	}
	public void setUpdateplayername(String updateplayername) {
		this.updateplayername = updateplayername;
	}
	public String getUpdatedateShow() {
		return updatedateShow;
	}
	public void setUpdatedateShow(String updatedateShow) {
		this.updatedateShow = updatedateShow;
	}
}
