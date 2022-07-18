package org.sg.loginweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.PlayerBo;

/**
* Player数据访问
* @author 帅哥
* @data 2022-06-21 05:08:04
 */

@Mapper
public interface PlayerMapper extends BaseMapper<Player>{
	
	public IPage<Player> selectPlayerByPage(IPage<Player> page,PlayerBo playerBo);
	
}
