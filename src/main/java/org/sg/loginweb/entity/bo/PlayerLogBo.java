package org.sg.loginweb.entity.bo;

import java.io.Serializable;

import org.sg.loginweb.util.Condition;

/**
* player_log条件类
* @author 帅哥
* @data 2022-06-30 21:36:02
 */

public class PlayerLogBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 玩家退出时间
	private String exitdate;
	// 玩家登陆日志
	private String id;
	// 玩家登陆时间
	private String logindate;
	// 玩家单次在线时间，不需要太精准
	private Integer logintime;
	// 玩家id
	private String playerid;
	private String playeridshow;
	// ip
	private String ip;
	private String ipcity;
	public String getExitdate() {
		return exitdate;
	}
	public void setExitdate(String exitdate) {
		this.exitdate = exitdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	public Integer getLogintime() {
		return logintime;
	}
	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}
	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpcity() {
		return ipcity;
	}
	public void setIpcity(String ipcity) {
		this.ipcity = ipcity;
	}
	public String getPlayeridshow() {
		return playeridshow;
	}
	public void setPlayeridshow(String playeridshow) {
		this.playeridshow = playeridshow;
	}
}
