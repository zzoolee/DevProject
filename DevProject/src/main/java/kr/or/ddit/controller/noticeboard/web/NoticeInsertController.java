package kr.or.ddit.controller.noticeboard.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {

	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form.do")
	public String noticeForm() {
		return "notice/form";
	}
	
//	@PreAuthorize("hasRole('')")
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String noticeInsert(
			HttpServletRequest req,
			HttpSession session,
			RedirectAttributes ra,
			NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요.");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요.");
		}
		
		if(errors.size() > 0) { // 에러 발생
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		}else {
			// 방법1 - 일반적인 session 처리시 사용
//			DDITMemberVO memberVO = (DDITMemberVO) session.getAttribute("SessionInfo");
			
//			if(memberVO != null) {
				// 로그인 후 등록된 세션 정보(로그인 한 회원 정보가 들어있음)
				// 회원 정보 중, id를 작성자로 셋팅
				// 방법2 - 스프링 시큐리티를 이용한 사용자명 처리
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				noticeVO.setBoWriter(memberVO.getMemId());
				noticeVO.setBoWriter(user.getUsername());
				
				ServiceResult result = noticeService.insertNotice(req, noticeVO);
				if(result.equals(ServiceResult.OK)) {
					goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
				}else {
					model.addAttribute("message", "서버 에러, 다시 시도해주세요!");
					goPage = "notice/form";
				}
//			}else {
//				ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다!");
//				goPage = "redirect:/notice/login.do";
//			}
		}		
		return goPage;
	}
}
