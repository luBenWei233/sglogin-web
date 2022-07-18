package org.sg.loginweb.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
* playerVo展示类
* @author 帅哥
* @data 2022-06-21 05:08:04
 */
public class PlayerVo implements Serializable {

private static final long serialVersionUID = 1L;

	// 封禁结束时间
	private Date banend;
	private String banendshow;
	// 是否封号：0正常、1封禁、2永封
	private Integer banif;
	private String banifshow;
	// 起封时间
	private Date banstart;
	private String banstartshow;
	// 绑定的邮箱
	private String email;
	// 玩家最近一次离线时间
	private Date exitDate;
	private String exitDateShow;
	// 玩家主键，UUID
	private String id;
	// 玩家最近一次登陆的时间
	private Date lastDate;
	private String lastDateShow;
	// 玩家最近一次登陆的IP
	private String lastip;
	private String lastipname;
	// 玩家在游戏中隐藏真实名称后的昵称
	private String nickname;
	// 玩家是否在线：0、不在，1、在线
	private Integer online;
	private String onlineshow;
	private Float pitch;
	// 玩家注册时的IP
	private String realip;
	private String realipname;
	// 我的世界是不区分大小写的
	private String realname;
	// 注册账号时间
	private Date regDate;
	// 玩家登陆账号、高版本MC可支持中文（到小写）
	private String username;
	// 玩家下线时所在的世界
	private String world;
	private Float x;
	private Float y;
	private Float yaw;
	private Float z;
	private String password;
	// 是否是管理员：0不是、1是
	private Integer adminif;
	private String adminshow;
	// 总在线时长，分钟
	private Integer countTime;
	//本次封禁时长
	private String banTime;
	//剩余封禁时长
	private String surbanTime;
	//被办理由
	private Integer banid;
	public Date getBanend() {
		return banend;
	}
	public void setBanend(Date banend) {
		this.banend = banend;
	}
	public Integer getBanif() {
		return banif;
	}
	public void setBanif(Integer banif) {
		this.banif = banif;
	}
	public Date getBanstart() {
		return banstart;
	}
	public void setBanstart(Date banstart) {
		this.banstart = banstart;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Float getPitch() {
		return pitch;
	}
	public void setPitch(Float pitch) {
		this.pitch = pitch;
	}
	public String getRealip() {
		return realip;
	}
	public void setRealip(String realip) {
		this.realip = realip;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public Float getYaw() {
		return yaw;
	}
	public void setYaw(Float yaw) {
		this.yaw = yaw;
	}
	public Float getZ() {
		return z;
	}
	public void setZ(Float z) {
		this.z = z;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAdminif() {
		return adminif;
	}
	public void setAdminif(Integer adminif) {
		this.adminif = adminif;
	}
	public String getBanifshow() {
		return banifshow;
	}
	public void setBanifshow(String banifshow) {
		this.banifshow = banifshow;
	}
	public String getOnlineshow() {
		return onlineshow;
	}
	public void setOnlineshow(String onlineshow) {
		this.onlineshow = onlineshow;
	}
	public String getAdminshow() {
		return adminshow;
	}
	public void setAdminshow(String adminshow) {
		this.adminshow = adminshow;
	}
	public String getLastDateShow() {
		return lastDateShow;
	}
	public void setLastDateShow(String lastDateShow) {
		this.lastDateShow = lastDateShow;
	}
	public String getLastipname() {
		return lastipname;
	}
	public void setLastipname(String lastipname) {
		this.lastipname = lastipname;
	}
	public String getExitDateShow() {
		return exitDateShow;
	}
	public void setExitDateShow(String exitDateShow) {
		this.exitDateShow = exitDateShow;
	}
	public String getBanendshow() {
		return banendshow;
	}
	public void setBanendshow(String banendshow) {
		this.banendshow = banendshow;
	}
	public String getBanstartshow() {
		return banstartshow;
	}
	public void setBanstartshow(String banstartshow) {
		this.banstartshow = banstartshow;
	}
	public Integer getCountTime() {
		return countTime;
	}
	public void setCountTime(Integer countTime) {
		this.countTime = countTime;
	}
	public String getBanTime() {
		return banTime;
	}
	public void setBanTime(String banTime) {
		this.banTime = banTime;
	}
	public String getSurbanTime() {
		return surbanTime;
	}
	public void setSurbanTime(String surbanTime) {
		this.surbanTime = surbanTime;
	}
	public String getRealipname() {
		return realipname;
	}
	public void setRealipname(String realipname) {
		this.realipname = realipname;
	}
	public Integer getBanid() {
		return banid;
	}
	public void setBanid(Integer banid) {
		this.banid = banid;
	}
}
