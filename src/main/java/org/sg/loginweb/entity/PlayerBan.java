package org.sg.loginweb.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
* player_ban表实体类
* @author 帅哥
* @data 2022-06-21 05:10:23
 */
@TableName("player_ban")
public class PlayerBan implements Serializable {

private static final long serialVersionUID = 1L;

	// 封禁时间，天时分秒
	@TableField(value = "bantime")
	private String bantime;
	// 封禁结束时间
	@TableField(value = "enddate")
	private Date enddate;
	// 处理人id
	@TableField(value = "handleid")
	private String handleid;
	@TableField(exist = false)
	private String handleidshow;
	// 玩家封禁主键
	@TableId(value="id",type=IdType.INPUT)
	private String id;
	// 玩家主键
	@TableField(value = "playerid")
	private String playerid;
	@TableField(exist = false)
	private String playeridshow;
	// 封禁开始时间
	@TableField(value = "startdate")
	private Date startdate;
	@TableField(value = "createdate")
	private Date createdate;
	@TableField(value = "reasonid")
	private Integer reasonid;
	@TableField(exist = false)
	private String reasonidshow;

	public String getBantime() {
		return bantime;
	}
	public void setBantime(String bantime) {
		this.bantime=bantime;
	}

	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
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

	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate=startdate;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
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
