package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.sg.loginweb.entity.PlayerLog;
import org.sg.loginweb.entity.bo.PlayerLogBo;
import org.sg.loginweb.entity.vo.PlayerLogVo;
import org.sg.loginweb.mapper.PlayerLogMapper;
import org.sg.loginweb.service.PlayerLogService;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* PlayerLog业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:13:02
 */

@Service
public class PlayerLogServiceImpl extends ServiceImpl<PlayerLogMapper,PlayerLog> implements PlayerLogService{

	@Autowired
	private PlayerLogMapper playerLogMapper;

	@Override
	public Result selectPlayerLogByPage(PlayerLogBo playerLogBo) {
		QueryWrapper<PlayerLog> query=new QueryWrapper<PlayerLog>();
		query.eq("playerid", playerLogBo.getPlayerid());
		Page<PlayerLog> playerLogs = playerLogMapper.selectPage(new Page<PlayerLog>(playerLogBo.getResPage(),playerLogBo.getResPageSize()), query);
		List<PlayerLogVo> playerLogVos=new ArrayList<PlayerLogVo>();
		for(PlayerLog playerLog:playerLogs.getRecords()) {
			PlayerLogVo playerLogVo=new PlayerLogVo();
			playerLogVo.setId(playerLog.getId());
			playerLogVo.setLogindate(MyUtil.DateToString(playerLog.getLogindate()));
			playerLogVo.setExitdate(MyUtil.DateToString(playerLog.getExitdate()));
			playerLogVo.setLogintime(playerLog.getLogintime());
			playerLogVo.setIp(playerLog.getIp());
			playerLogVo.setIpcity(MyUtil.getIpAtCityName(playerLog.getIp()));
			playerLogVos.add(playerLogVo);
		}
		return new Result(playerLogs,playerLogVos);
	}

}
