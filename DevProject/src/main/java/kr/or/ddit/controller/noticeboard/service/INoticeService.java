package kr.or.ddit.controller.noticeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {

	public DDITMemberVO loginCheck(DDITMemberVO member);
	public ServiceResult idCheck(String memId);
	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVO);
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO);
	public NoticeVO selectNotice(int boNo);
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO noticeVO);
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo);
	public String findId(DDITMemberVO memberVO);
	public String findPw(DDITMemberVO memberVO);
	
	public DDITMemberVO selectMember(String memId);
	public ServiceResult profileUpdate(HttpServletRequest req, DDITMemberVO memberVO);
	public NoticeFileVO noticeDownload(int fileNo);

}
