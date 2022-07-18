package org.sg.loginweb.entity.vo;

import java.io.Serializable;

/**
* player_ban展示层
* @author 帅哥
* @data 2022-06-29 23:58:23
 */
public class PlayerBanVo implements Serializable {

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
	private String createdate;
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
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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
}
