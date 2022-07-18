package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
* player_log表实体类
* @author 帅哥
* @data 2022-06-21 05:13:02
 */
@TableName("player_log")
public class PlayerLog implements Serializable {

private static final long serialVersionUID = 1L;

	// 玩家退出时间
	@TableField(value = "exitdate")
	private Date exitdate;
	// 玩家登陆日志
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 玩家登陆时间
	@TableField(value = "logindate")
	private Date logindate;
	// 玩家单次在线时间，不需要太精准
	@TableField(value = "logintime")
	private Integer logintime;
	// 玩家id
	@TableField(value = "playerid")
	private String playerid;
	// ip
	@TableField(value = "ip")
	private String ip;

	public Date getExitdate() {
		return exitdate;
	}
	public void setExitdate(Date exitdate) {
		this.exitdate=exitdate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate=logindate;
	}

	public Integer getLogintime() {
		return logintime;
	}
	public void setLogintime(Integer logintime) {
		this.logintime=logintime;
	}

	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid=playerid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
