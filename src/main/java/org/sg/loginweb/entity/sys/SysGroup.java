package org.sg.loginweb.entity.sys;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* sys_group表实体类
* @author 帅哥
* @data 2022-06-21 05:22:18
 */
@TableName("sys_group")
public class SysGroup implements Serializable {

private static final long serialVersionUID = 1L;

	// 字典类型主键
	@TableId(value="id",type=IdType.AUTO)
	private Integer id;
	// 字典类型值
	@TableField(value = "value")
	private String value;
	// 排序
	@TableField(value = "sort")
	private Integer sort;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value=value;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
