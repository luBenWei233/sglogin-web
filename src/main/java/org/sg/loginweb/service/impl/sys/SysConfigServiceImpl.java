package org.sg.loginweb.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sg.loginweb.cache.SysConfigCache;
import org.sg.loginweb.entity.sys.SysConfig;
import org.sg.loginweb.mapper.sys.SysConfigMapper;
import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.util.MyEnum;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* SysConfig业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:20:52
 */

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper,SysConfig> implements SysConfigService{
	
	private static final Log LOG= LogFactory.getLog(SysConfigServiceImpl.class);
	
	//自定义换行符
	private static final String n="#n#";

	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	@Value("${spring.datasource.url}")
	private String dataBaseUrl;
	
	@Value("${custom.dataurl}")
	private String dataurl;

	@Override
	public Map<String,String> sysConfigCache(String... key) {
		if(SysConfigCache.getMap()==null) {
			initCache();
		}
		Map<String,String> pageCache=new HashMap<String, String>();
		for(String k:key) {
			pageCache.put(k, SysConfigCache.getMap().get(k));
		}
		return pageCache;
	}
	
	//初始化系统配置缓存
	public void initCache() {
		SysConfigCache.newMap();
		List<SysConfig> sysConfigs=sysConfigMapper.selectList(new QueryWrapper<SysConfig>().select("id","value"));
		for(SysConfig sys:sysConfigs) {
			sys.setValue(sys.getValue()==null?"":sys.getValue());
			if(sys.getValue().contains(n)) {
				for(Map.Entry<String,String> entry:getsysConfigMap(sys).entrySet()) {
					SysConfigCache.getMap().put(entry.getKey(), entry.getValue());
				}
			} else {
				SysConfigCache.getMap().put(sys.getId(),sys.getValue());
			}
		}
	}
	
	//将单个配置，转为多个配置
	private Map<String,String> getsysConfigMap(SysConfig sysConfig){
		String[] value=sysConfig.getValue().split(n);
		Map<String,String> map=new HashMap<String, String>();
		for(int i=0;i<value.length;i++) {
			map.put(sysConfig.getId()+(i+1), value[i]);
		}
		return map;
	}

	@Override
	public Result selectSysConfigByPage(SysConfig sysConfig) {
		QueryWrapper<SysConfig> query=new QueryWrapper<SysConfig>();
		query.select("id","sys_config.value","sys_config.change","remarks");
		if(sysConfig.getChange()!=null && sysConfig.getChange()!=-1) {
			query.eq("sys_config.change", sysConfig.getChange());
		}
		if(sysConfig.getRemarks()!=null && !"".equals(sysConfig.getRemarks())) {
			query.like("remarks", sysConfig.getRemarks());
		}
		Page<SysConfig> sysConfigs = sysConfigMapper.selectPage(new Page<SysConfig>(sysConfig.getResPage(),sysConfig.getResPageSize()), query);
		for(SysConfig config:sysConfigs.getRecords()) {
			if(MyEnum.SQLITE.getValue().equals(MyUtil.getDataBaseType()) && "deploydate".equals(config.getId())) {
				config.setValue(MyUtil.timeStamp2Date(config.getValue().substring(0,10)));
				break;
			}
		}
		return new Result(sysConfigs);
	}

	@Override
	public int updateSysConfig(SysConfig sysConfig) {
		UpdateWrapper<SysConfig> update=new UpdateWrapper<SysConfig>();
		if(MyEnum.MYSQL.getValue().equals(MyUtil.getDataBaseType())){
			update.set("sys_config.value", sysConfig.getValue()).set("updatedate", new Date()).set("updateplayerid", sysConfig.getUpdateplayerid())
			.eq("id", sysConfig.getId()).eq("sys_config.change", 1);
		} else {
			update.set("value", sysConfig.getValue()).set("updatedate", new Date()).set("updateplayerid", sysConfig.getUpdateplayerid())
			.eq("id", sysConfig.getId()).eq("change", 1);
		}
		int count=sysConfigMapper.update(new SysConfig(), update);
		if(SysConfigCache.getMap()==null) {
			initCache();
		}
		if(count>0) {
			if(sysConfig.getValue().contains(n)) {
				for(Map.Entry<String,String> entry:getsysConfigMap(sysConfig).entrySet()) {
					SysConfigCache.getMap().put(entry.getKey(), entry.getValue());
				}
			} else {
				SysConfigCache.getMap().put(sysConfig.getId(),sysConfig.getValue());
			}
		}
		return count;
	}

	@Override
	public SysConfig selectSysConfigById(String id) {
		SysConfig sysConfig = sysConfigMapper.selectSysConfigById(id);
		if(MyEnum.SQLITE.getValue().equals(MyUtil.getDataBaseType()) && "deploydate".equals(id)) {
			sysConfig.setValue(MyUtil.timeStamp2Date(sysConfig.getValue().substring(0,10)));
		}
		if(sysConfig.getUpdatedate()!=null) {
			sysConfig.setUpdatedateShow(MyUtil.DateToString(sysConfig.getUpdatedate()));
		}
		return sysConfig;
	}

	@Transactional
	@Override
	public void getTableCreate() {
		if(dataBaseUrl.contains(MyEnum.MYSQL.getValue())) {
			String tableName=sysConfigMapper.getTableExists();
			if(tableName==null || !"player".equals(tableName)) {
				try {
					for(String sql:MyUtil.readTextFileToString(this.getClass().getClassLoader().getResourceAsStream("mybatis/database.sql")).split("#line#")) {
						sysConfigMapper.createTableBySgLogin(sql);
					}
					for(String sql:MyUtil.readTextFileToList(this.getClass().getClassLoader().getResourceAsStream("mybatis/insert.sql"))) {
						sysConfigMapper.insertInitData(sql);
					}
					LOG.info("成功创建表和添加数据！");
					updateSystemInitDate();
				} catch (Exception e) {
					LOG.error("创建表或添加数据错误，请检查数据库！");
					e.printStackTrace();
				}
			}
		}else {
			String pathAndName=dataBaseUrl.split(dataurl)[1];
			File file = new File(pathAndName);
			if(file.length()==0) {
				try {
					MyUtil.writeFileToLoaction(this.getClass().getClassLoader().getResourceAsStream("mybatis/sglogin.sqlite3"),pathAndName);
					LOG.info("成功创建sglogin.sqlite3！");
					updateSystemInitDate();
				} catch (Exception e) {
					LOG.error("创建sglogin.sqlite3错误，请检查！");
					e.printStackTrace();
				}
			}
		}
	}
	
	private void updateSystemInitDate() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Date date=new Date();
					// 如果三分钟都启动不起来，那么此服务器肯定开不了我的世界！
					Thread.sleep(1000*180);
					UpdateWrapper<SysConfig> update=new UpdateWrapper<SysConfig>();
					if(MyEnum.MYSQL.getValue().equals(MyUtil.getDataBaseType())){
						update.set("sys_config.value",MyUtil.DateToString(date)).eq("id","deploydate");
					} else {
						update.set("value",date).eq("id","deploydate");
					}
					sysConfigMapper.update(new SysConfig(), update);
					LOG.error("修改系统部署时间成功！");
				} catch (InterruptedException e) {
					LOG.error("修改系统部署时间失败，但关系不大！可以正常使用！");
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
