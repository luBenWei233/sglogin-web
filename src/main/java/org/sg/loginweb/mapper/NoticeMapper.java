package org.sg.loginweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.sg.loginweb.entity.Notice;
import org.sg.loginweb.entity.bo.NoticeBo;

/**
* Notice数据访问
* @author 帅哥
* @data 2022-06-21 05:15:22
 */

@Mapper
public interface NoticeMapper extends BaseMapper<Notice>{
	public IPage<Notice> selectNoticeByPage(IPage<Notice> page,NoticeBo noticeBo);
	public IPage<Notice> selectNoticeByShow(IPage<Notice> page,NoticeBo noticeBo);
	public Notice selectNoticeById(String id);
}
