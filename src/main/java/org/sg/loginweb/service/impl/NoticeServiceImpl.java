package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.sg.loginweb.entity.Notice;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.NoticeBo;
import org.sg.loginweb.entity.vo.NoticeVo;
import org.sg.loginweb.mapper.NoticeMapper;
import org.sg.loginweb.service.NoticeService;
import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.util.MyEnum;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* Notice业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:15:22
 */

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private SysConfigService sysConfigService;

	@Override
	public int insertNotice(NoticeBo noticeBo) {
		Notice notice=new Notice();
		notice.setId(UUID.randomUUID().toString());
		notice.setTitle(noticeBo.getTitle());
		notice.setContent(noticeBo.getContent());
		notice.setFileid(noticeBo.getFileid());
		notice.setValid(noticeBo.getValid());
		notice.setIftop(noticeBo.getIftop());
		notice.setCreatedate(new Date());
		notice.setCreateplayerid(noticeBo.getCreateplayerid());
		if(noticeBo.getTiming()!=null && !"".equals(noticeBo.getTiming())) {
			notice.setTiming(MyUtil.StringToDate(noticeBo.getTiming()));
		}else {
			notice.setTiming(new Date());
		}
		return noticeMapper.insert(notice);
	}

	@Override
	public Result selectNoticeByPage(NoticeBo noticeBo) {
		if(noticeBo.getTiming1()!=null && !"".equals(noticeBo.getTiming1())) {
			noticeBo.setTiming1_sql(MyUtil.StringToStringDate(noticeBo.getTiming1()));
		}
		if(noticeBo.getTiming1()!=null && !"".equals(noticeBo.getTiming1())) {
			noticeBo.setTiming1_sql(MyUtil.StringToStringDate(noticeBo.getTiming1()));
		}
		IPage<Notice> notices = noticeMapper.selectNoticeByPage(new Page<Notice>(noticeBo.getResPage(),noticeBo.getResPageSize()), noticeBo);
		List<NoticeVo> noticeVos=new ArrayList<NoticeVo>();
		for(Notice notice:notices.getRecords()) {
			NoticeVo noticeVo=new NoticeVo();
			noticeVo.setId(notice.getId());
			noticeVo.setTitle(notice.getTitle());
			noticeVo.setValidname(notice.getValid()==1?"已发布":"未发布");
			noticeVo.setIftopname(notice.getIftop()==1?"置顶":"不置顶");
			noticeVo.setTiming(MyUtil.DateToString(notice.getTiming()));
			noticeVo.setCreateplayerid(notice.getCreateplayerid());
			noticeVo.setCreateplayername(notice.getCreateplayername());
			noticeVos.add(noticeVo);
		}
		return new Result(notices,noticeVos);
	}

	@Override
	public NoticeVo selectNoticeById(String id) {
		Notice notice = noticeMapper.selectNoticeById(id);
		NoticeVo noticeVo=new NoticeVo();
		noticeVo.setId(notice.getId());
		noticeVo.setTitle(notice.getTitle());
		noticeVo.setContent(notice.getContent());
		noticeVo.setValid(notice.getValid());
		noticeVo.setIftop(notice.getIftop());
		noticeVo.setFileid(notice.getFileid());
		noticeVo.setTiming(MyUtil.DateToTString(notice.getTiming()));
		noticeVo.setFilepath(MyEnum.IMAGE_PATH.getValue()+notice.getFilepath());
		return noticeVo;
	}

	@Override
	public int updateNotice(NoticeBo noticeBo) {
		Notice notice=new Notice();
		notice.setId(noticeBo.getId());
		notice.setTitle(noticeBo.getTitle());
		notice.setContent(noticeBo.getContent());
		notice.setFileid(noticeBo.getFileid());
		notice.setValid(noticeBo.getValid());
		notice.setIftop(noticeBo.getIftop());
		notice.setUpdatedate(new Date());
		notice.setUpdateplayerid(noticeBo.getUpdateplayerid());
		if(noticeBo.getTiming()!=null && !"".equals(noticeBo.getTiming())) {
			notice.setTiming(MyUtil.StringToDate(noticeBo.getTiming()));
		}else {
			notice.setTiming(new Date());
		}
		return noticeMapper.updateById(notice);
	}

	@Override
	public int deleteNotice(String id) {
		return noticeMapper.deleteById(id);
	}

	@Override
	public String noticeShow(Player player,Integer page, String id) {
		NoticeBo noticeBo=new NoticeBo();
		if(player!=null && player.getAdminif()==1) {
			noticeBo.setId(id);
		}
		IPage<Notice> notices = noticeMapper.selectNoticeByShow(new Page<Notice>(page,2), noticeBo);
		StringBuffer buffer=new StringBuffer();
		for(Notice notice:notices.getRecords()) {
			buffer.append("<h2 class='h2'>"+notice.getTitle()
			+"&nbsp;<small>"+sysConfigService.sysConfigCache("releasedate").get("releasedate").toString()
			+MyUtil.DateToString(notice.getTiming())+"</small></h2>");
			
			buffer.append("<div class='showDiv'>"+notice.getContent()+"</div>");
			if(notice.getFilepath()!=null) {
				buffer.append(MyUtil.imageToHtml(MyEnum.IMAGE_PATH.getValue()+notice.getFilepath()));
			}
		}
		return buffer.toString();
	}

}