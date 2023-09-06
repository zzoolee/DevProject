package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JSPHomeController {
	
	/*
	 * 7장 JSP
	 */
	
	// 1) 자바빈즈 프로퍼티를 조회하는 경우 "속성명.프로퍼티명"을 지정한다.
	@RequestMapping(value="/home0101", method = RequestMethod.GET)
	public String home0101(Model model) {
		Member member = new Member();
		member.setUserId("hongkildong");
		member.setPassword("1122");
		member.setEmail("ddit@n.com");
		member.setUserName("홍길동");
		model.addAttribute("member", member);
		return "home/home0101";
	}
	
	// 
	@RequestMapping(value="/home0102", method = RequestMethod.GET)
	public String home0102(Model model) {
		Map<String, String> memberMap = new HashMap<String, String>();
		memberMap.put("userId", "hongkd");
		memberMap.put("password", "1234");
		memberMap.put("email", "ddit@n.com");
		memberMap.put("userName", "홍길동");
		model.addAttribute("memberMap", memberMap);
		return "home/home0102";
	}
	
	// 산술 연산자
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
		int coin = 1000;
		model.addAttribute("coin", coin);
		return "home/home0201";
	}
	
	// 비교 연산자(숫자 비교)
	@RequestMapping(value="/home0202", method = RequestMethod.GET)
	public String home0202(Model model) {
		int coin = 1000;
		model.addAttribute("coin", coin);
		return "home/home0202";
	}
	
	// 비교 연산자(문자 비교)
	@RequestMapping(value="/home0203", method = RequestMethod.GET)
	public String home0203(Model model) {
		String userId = "hongkd";
		model.addAttribute("userId", userId);
		return "home/home0203";
	}
	
	// empty 연산자
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "home/home0301";
	}
	
	// 논리 연산자
	@RequestMapping(value="/home0401", method = RequestMethod.GET)
	public String home0401(Model model) {
		int coin = 1000;
		String userId = "hongkd";
		Member member = new Member();
		model.addAttribute("coin", coin);
		model.addAttribute("userId", userId);
		model.addAttribute("member", member);
		return "home/home0401";
	}
}
