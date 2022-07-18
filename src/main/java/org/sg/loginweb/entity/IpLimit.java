package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
* ip_limit表实体类
* @author 帅哥
* @data 2022-06-21 05:15:54
 */
@TableName("ip_limit")
public class IpLimit implements Serializable {

private static final long serialVersionUID = 1L;

	// ip
	@TableField(value = "ip")
	private String ip;
	// 玩家账号
	@TableId(value="playername",type=IdType.INPUT)
	private String playername;
	// 注册时间
	@TableField(value = "regdate")
	private Date regdate;

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip=ip;
	}

	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername=playername;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate=regdate;
	}
}
