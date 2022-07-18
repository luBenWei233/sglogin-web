package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sg.loginweb.entity.Notice;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.NoticeBo;
import org.sg.loginweb.entity.vo.NoticeVo;
import org.sg.loginweb.util.Result;

/**
* Notice业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:15:22
 */

public interface NoticeService extends IService<Notice>{
	public int insertNotice(NoticeBo noticeBo);
	public Result selectNoticeByPage(NoticeBo noticeBo);
	public NoticeVo selectNoticeById(String id);
	public int updateNotice(NoticeBo noticeBo);
	public int deleteNotice(String id);
	public String noticeShow(Player player,Integer page,String id);
}
