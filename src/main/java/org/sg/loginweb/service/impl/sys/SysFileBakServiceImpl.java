package org.sg.loginweb.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sg.loginweb.entity.sys.SysFileBak;
import org.sg.loginweb.mapper.sys.SysFileBakMapper;
import org.sg.loginweb.service.sys.SysFileBakService;

/**
* SysFileBak业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:21:51
 */

@SuppressWarnings("all")
@Service
public class SysFileBakServiceImpl extends ServiceImpl<SysFileBakMapper,SysFileBak> implements SysFileBakService{

	@Autowired
	private SysFileBakMapper sysFileBakMapper;

}
