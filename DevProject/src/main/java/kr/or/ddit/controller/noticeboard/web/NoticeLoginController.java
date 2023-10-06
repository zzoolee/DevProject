package kr.or.ddit.controller.noticeboard.web;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeLoginController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String noticeLogin(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/login";
	}
	
	@RequestMapping(value="/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest req, DDITMemberVO member, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(member.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(member.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		
		if(errors.size() > 0) { // 에러 발생
			model.addAttribute("errors", errors);
			model.addAttribute("member", member);
			model.addAttribute("bodyText", "login-page");
			goPage = "conn/login";
		}else{
			DDITMemberVO memberVO = noticeService.loginCheck(member);
			if(memberVO != null) { // 로그인 성공
				// 로그인 성공 후, 세션 처리
				HttpSession session = req.getSession();
				session.setAttribute("SessionInfo", memberVO);
				goPage = "redirect:/notice/list.do";
//				goPage = "redirect:/notice/otp.do";
			}else {
				model.addAttribute("message", "서버에러, 로그인 정보를 정확하게 입력해주세요.");
				model.addAttribute("member", member);
				model.addAttribute("bodyText", "login-page");
				goPage = "conn/login";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.GET)
	public String noticeSignupForm(Model model) {
		model.addAttribute("bodyText", "register-page");
		return "conn/register";
	}
	
	@ResponseBody // 데이터를 리턴하기 때문에 필요하다
	@RequestMapping(value="/idCheck.do", method = RequestMethod.POST)
	public ResponseEntity<ServiceResult> idCheck(@RequestBody Map<String, String> map){
		/*
		 * 단일 데이터를 꺼낼 때,
		 * 1) ajax 설정에서 ContentType 설정을 하지 않고 데이터만 {memId : id} 설정해서 넘길때
		 * 	- String memId로 데이터를 꺼낼 수 있다.
		 * 
		 * 2) ajax 설정에서 ContentType 설정을 하지 않고, 데이터만 JSON.stringify일 때
		 * 	- @RequestBody로 String memID를 꺼내면 '%7B%22memId%22%3A...' 이런 데이터가 넘어옴
		 * 
		 * 3) ajax 설정에서 ContentType 설정을 하고, 데이터만 JSON.stringify일 때(데이터 JSON 객체로 넘어감)
		 * 	- @RequestParam으로 String memId를 꺼내면 400에러가 발생한다.
		 * 
		 * 4) ajax 설정에서 ContentType 설정을 하고, 데이터만 JSON.stringify일 때(데이터 JSON 객체로 넘어감)
		 * 	- @RequestBody Map<String, String> map으로 꺼내면 'a001' 데이터가 넘어온다
		 */
		
		String id = map.get("memId").toString();
		log.info("넘겨받은 아이디 : " + id);		
		ServiceResult result = noticeService.idCheck(id);
		return new ResponseEntity<ServiceResult>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.POST)
	public String signup(
			HttpServletRequest req,
			DDITMemberVO memberVO, Model model, RedirectAttributes ra) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemName())) {
			errors.put("memName", "이름을 입력해주세요.");
		}
		
		if(errors.size() > 0) { // 에러가 발생
			model.addAttribute("bodyText", "register-page");
			model.addAttribute("errors", errors);
			model.addAttribute("member", memberVO);
		}else {
			ServiceResult result = noticeService.signup(req, memberVO);
			if(result.equals(ServiceResult.OK)) {
				ra.addFlashAttribute("message", "회원가입을 완료하였습니다!"); // 일회성 메세지
				goPage = "redirect:/notice/login.do";
			}else {
				model.addAttribute("bodyText", "register-page");
				model.addAttribute("message", "서버에러, 다시 시도해주세요!");
				model.addAttribute("member", memberVO);
				goPage = "conn/register";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/forget.do", method = RequestMethod.GET)
	public String noticeForget(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/forget";
	}
	
	@RequestMapping(value="/findid.do", method = RequestMethod.POST)
	public String findid(DDITMemberVO memberVO, Model model) {
		String memId = noticeService.findId(memberVO);
		if(memId != null) {
			model.addAttribute("memId", memId);
		}else {
			model.addAttribute("message", "해당하는 회원이 존재하지 않습니다.");
		}
		model.addAttribute("bodyText", "login-page");
		return "conn/forget";
	}
	
	@RequestMapping(value="/findpw.do", method = RequestMethod.POST)
	public String findpw(DDITMemberVO memberVO, Model model) {
		String memPw = noticeService.findPw(memberVO);
		if(memPw != null) {
			model.addAttribute("memPw", memPw);
		}else {
			model.addAttribute("message", "해당하는 회원이 존재하지 않습니다.");
		}
		model.addAttribute("bodyText", "login-page");
		return "conn/forget";
	}
	
//	@RequestMapping(value="/otp.do")
//	public String otp(HttpServletRequest request, ModelMap model) throws Exception {
//
//		byte[] buffer = new byte[5 + 5 * 5];
//	    new Random().nextBytes(buffer);
//	    Base32 codec = new Base32();
//	    byte[] secretKey = Arrays.copyOf(buffer, 10);  // 16자 이상이어야 하므로 10으로 설정 필요
//	    byte[] bEncodedKey = codec.encode(secretKey);
//
//	    // 인증키 생성
//	    String encodedKey = new String(bEncodedKey);
//	    // 바코드 주소 생성
//	    HttpSession session = request.getSession();
//	    DDITMemberVO dditMemberVO = (DDITMemberVO) session.getAttribute("SessionInfo");
//	    String memId = dditMemberVO.getMemId();
//	    String qrUrl = getQRBarcodeURL(memId, "ddit.com", encodedKey);
//	    
//	    model.addAttribute("encodedKey", encodedKey);
//	    model.addAttribute("qrUrl", qrUrl);
//	    model.addAttribute("bodyText", "login-page");
//	 
//	    return "conn/otp";
//	}
//	
//	// 바코드 생성 함수
//	public static String getQRBarcodeURL(String user, String host, String secret) {
//	    String format = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
//	    return String.format(format, user, host, secret);
//	}
//	
//	@RequestMapping(value ="/otpCheck.do", method = RequestMethod.POST)
//	public String otpCheck(String code, String encodedKey, RedirectAttributes ra) throws Exception {
//		
//		log.info(code);	
//		
//		long codeCheck = Integer.parseInt(code);
//		long l = new Date().getTime();
//		long ll =  l / 30000;
//		 
//		boolean check_code = false;
//		check_code = check_code(encodedKey, codeCheck, ll);
//		 
//		if(!check_code) {
//			ra.addFlashAttribute("errorMsg", "승인 거부되었습니다.");
//		    return "redirect:/notice/otp.do";
//		}
//		return "redirect:/notice/list.do";
//	}
//	
//	// 코드 체크 함수
//	private static boolean check_code(String secret, long code, long t) throws InvalidKeyException, NoSuchAlgorithmException {
//	  Base32 codec = new Base32();
//	  byte[] decodedKey = codec.decode(secret);
//	 
//	  int window = 3;
//	  for (int i = -window; i <= window; ++i) {
//	      long hash = verify_code(decodedKey, t + i);
//	 
//	      if (hash == code) {
//	          return true;
//	      }
//	  }	 
//	  return false;
//	}
//	
//	// 코드 확인 함수
//	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
//	  byte[] data = new byte[8];
//	  long value = t;
//	  for (int i = 8; i-- > 0; value >>>= 8) {
//	      data[i] = (byte) value;
//	  }
//	 
//	  SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
//	  Mac mac = Mac.getInstance("HmacSHA1");
//	  mac.init(signKey);
//	  byte[] hash = mac.doFinal(data);
//	 
//	  int offset = hash[20 - 1] & 0xF;
//	 
//	  long truncatedHash = 0;
//	  for (int i = 0; i < 4; ++i) {
//	      truncatedHash <<= 8;
//	      truncatedHash |= (hash[offset + i] & 0xFF);
//	  }
//	 
//	  truncatedHash &= 0x7FFFFFFF;
//	  truncatedHash %= 1000000;
//	 
//	  return (int) truncatedHash;
//	}

}


