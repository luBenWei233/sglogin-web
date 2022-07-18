package org.sg.loginweb.entity.bo;

import java.io.Serializable;
import org.sg.loginweb.util.Condition;

/**
* player_ban数据接收
* @author 帅哥
* @data 2022-06-29 23:58:23
 */
public class PlayerBanBo extends Condition implements Serializable {

private static final long serialVersionUID = 1L;

	// 封禁时间，天时分秒
	private String bantime;
	// 封禁结束时间
	private String enddate;
	// 处理人id
	private String handleid;
	private String handleidshow;
	// 玩家封禁主键
	private String id;
	// 玩家主键
	private String playerid;
	private String playeridshow;
	// 封禁开始时间
	private String startdate;
	private String createdate1;
	private String createdate1_sql;
	private String createdate2;
	private String createdate2_sql;
	private Integer reasonid;
	private String reasonidshow;

	public String getBantime() {
		return bantime;
	}
	public void setBantime(String bantime) {
		this.bantime=bantime;
	}

	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate=enddate;
	}

	public String getHandleid() {
		return handleid;
	}
	public void setHandleid(String handleid) {
		this.handleid=handleid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}

	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid=playerid;
	}

	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate=startdate;
	}
	public Integer getReasonid() {
		return reasonid;
	}
	public void setReasonid(Integer reasonid) {
		this.reasonid = reasonid;
	}
	public String getHandleidshow() {
		return handleidshow;
	}
	public void setHandleidshow(String handleidshow) {
		this.handleidshow = handleidshow;
	}
	public String getPlayeridshow() {
		return playeridshow;
	}
	public void setPlayeridshow(String playeridshow) {
		this.playeridshow = playeridshow;
	}
	public String getReasonidshow() {
		return reasonidshow;
	}
	public void setReasonidshow(String reasonidshow) {
		this.reasonidshow = reasonidshow;
	}
	public String getCreatedate1() {
		return createdate1;
	}
	public void setCreatedate1(String createdate1) {
		this.createdate1 = createdate1;
	}
	public String getCreatedate1_sql() {
		return createdate1_sql;
	}
	public void setCreatedate1_sql(String createdate1_sql) {
		this.createdate1_sql = createdate1_sql;
	}
	public String getCreatedate2() {
		return createdate2;
	}
	public void setCreatedate2(String createdate2) {
		this.createdate2 = createdate2;
	}
	public String getCreatedate2_sql() {
		return createdate2_sql;
	}
	public void setCreatedate2_sql(String createdate2_sql) {
		this.createdate2_sql = createdate2_sql;
	}
}
