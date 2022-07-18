package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
* player表实体类
* @author 帅哥
* @data 2022-06-21 05:08:04
 */
@TableName("player")
public class Player implements Serializable {

private static final long serialVersionUID = 1L;

	// 封禁结束时间
	@TableField(value = "banend")
	private Date banend;
	// 是否封号：0正常、1封禁、2永封
	@TableField(value = "banif")
	private Integer banif;
	// 起封时间
	@TableField(value = "banstart")
	private Date banstart;
	// 绑定的邮箱
	@TableField(value = "email")
	private String email;
	// 玩家最近一次离线时间
	@TableField(value = "exitDate")
	private Date exitDate;
	// 玩家主键，UUID
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 玩家最近一次登陆的时间
	@TableField(value = "lastDate")
	private Date lastDate;
	// 玩家最近一次登陆的IP
	@TableField(value = "lastip")
	private String lastip;
	// 玩家在游戏中隐藏真实名称后的昵称
	@TableField(value = "nickname")
	private String nickname;
	// 玩家是否在线：0、不在，1、在线
	@TableField(value = "online")
	private Integer online;
	// 
	@TableField(value = "pitch")
	private Float pitch;
	// 玩家注册时的ID
	@TableField(value = "realip")
	private String realip;
	// 我的世界是不区分大小写的
	@TableField(value = "realname")
	private String realname;
	// 注册账号时间
	@TableField(value = "regDate")
	private Date regDate;
	// 玩家登陆账号、高版本MC可支持中文（到小写）
	@TableField(value = "username")
	private String username;
	// 玩家下线时所在的世界
	@TableField(value = "world")
	private String world;
	// 
	@TableField(value = "x")
	private Float x;
	// 
	@TableField(value = "y")
	private Float y;
	// 
	@TableField(value = "yaw")
	private Float yaw;
	// 
	@TableField(value = "z")
	private Float z;
	@TableField(value = "password")
	private String password;
	// 是否是管理员：0不是、1是
	@TableField(value = "adminif")
	private Integer adminif;
	// 总在线时长，分钟
	@TableField(value = "countTime")
	private Integer countTime;
	//被办理由
	@TableField(value = "banid")
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
	public Integer getCountTime() {
		return countTime;
	}
	public void setCountTime(Integer countTime) {
		this.countTime = countTime;
	}
	public Integer getBanid() {
		return banid;
	}
	public void setBanid(Integer banid) {
		this.banid = banid;
	}
}
