package org.sg.loginweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.sg.loginweb.entity.PlayerBan;
import org.sg.loginweb.entity.bo.PlayerBanBo;

/**
* PlayerBan数据访问
* @author 帅哥
* @data 2022-06-21 05:10:23
 */

@Mapper
public interface PlayerBanMapper extends BaseMapper<PlayerBan>{
	
	public IPage<PlayerBan> selectPlayerBanByPage(IPage<PlayerBan> page,PlayerBanBo playerBanBo);
	
	public PlayerBan selectPlayerBanById(String id);
	
}
