package org.sg.loginweb.entity.bo;

import java.io.Serializable;
import java.util.Date;
import org.sg.loginweb.util.Condition;

/**
* player玩家表bo
* @author 帅哥
* @data 2022-06-21 06:44
 */
public class PlayerBo extends Condition implements Serializable {

	private static final long serialVersionUID = 1L;

	// 封禁结束时间
	private Date banend;
	private String banendshow;
	// 是否封号：0正常、1封禁、2永封
	private Integer banif;
	// 起封时间
	private Date banstart;
	private String banstartshow;
	// 绑定的邮箱
	private String email;
	// 玩家最近一次离线时间
	private Date exitDate;
	// 玩家主键，UUID
	private String id;
	// 玩家最近一次登陆的时间
	private Date lastDate;
	// 玩家最近一次登陆的IP
	private String lastip;
	// 玩家在游戏中隐藏真实名称后的昵称
	private String nickname;
	//验证码
	private String code;
	private String password;
	private Float pitch;
	private String realip;
	private String realname;
	private Date regDate;
	private String username;
	private String world;
	private Float x;
	private Float y;
	private Float yaw;
	private Float z;
	private String password2;
	// 是否是管理员：0不是、1是
	private Integer adminif;
	// 是否在线：0不在、1在线
	private Integer online;
	private String regDate1;
	private String regDate1_sql;
	private String regDate2;
	private String regDate2_sql;
	private String lastDate1;
	private String lastDate1_sql;
	private String lastDate2;
	private String lastDate2_sql;
	private Integer orderById;
	private Integer banid;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public Integer getAdminif() {
		return adminif;
	}
	public void setAdminif(Integer adminif) {
		this.adminif = adminif;
	}
	public String getRegDate1() {
		return regDate1;
	}
	public void setRegDate1(String regDate1) {
		this.regDate1 = regDate1;
	}
	public String getRegDate2() {
		return regDate2;
	}
	public void setRegDate2(String regDate2) {
		this.regDate2 = regDate2;
	}
	public String getLastDate1() {
		return lastDate1;
	}
	public void setLastDate1(String lastDate1) {
		this.lastDate1 = lastDate1;
	}
	public String getLastDate2() {
		return lastDate2;
	}
	public void setLastDate2(String lastDate2) {
		this.lastDate2 = lastDate2;
	}
	public Integer getOrderById() {
		return orderById;
	}
	public void setOrderById(Integer orderById) {
		this.orderById = orderById;
	}
	public String getRegDate1_sql() {
		return regDate1_sql;
	}
	public void setRegDate1_sql(String regDate1_sql) {
		this.regDate1_sql = regDate1_sql;
	}
	public String getRegDate2_sql() {
		return regDate2_sql;
	}
	public void setRegDate2_sql(String regDate2_sql) {
		this.regDate2_sql = regDate2_sql;
	}
	public String getLastDate1_sql() {
		return lastDate1_sql;
	}
	public void setLastDate1_sql(String lastDate1_sql) {
		this.lastDate1_sql = lastDate1_sql;
	}
	public String getLastDate2_sql() {
		return lastDate2_sql;
	}
	public void setLastDate2_sql(String lastDate2_sql) {
		this.lastDate2_sql = lastDate2_sql;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getBanid() {
		return banid;
	}
	public void setBanid(Integer banid) {
		this.banid = banid;
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
}
