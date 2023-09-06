package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag")
public class JSPFormTagController {
	/*
	 * 8장 스프링 폼 태그
	 */
	
	@RequestMapping(value="/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...!");
		model.addAttribute("member", new Member());
		// 클라이언트가 폼 태그 안에서 사용(modelAttribute에서 key 지정)할 여러 자원 카테고리(Member, Board...)가 서버 내에 만들어져 있어야 함(Model로 key 전달)
		return "home/formtag/registerForm01";
	}
	
	// 2) 폼 객체의 속성명과 스프링 폼 태그의 modelAttribute 속성값이 일치하지 않으면 에러가 발생한다.
	@RequestMapping(value="/registerForm02", method = RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행...!");
		model.addAttribute("user", new Member()); // 불일치로 오류
		return "home/formtag/registerForm01";
	}
	
	// 3) 컨트롤러 메서드의 매개변수로 자바빈즈 객체가 전달이 되면 기본적으로 다시 화면으로 전달된다.
	@RequestMapping(value="/registerForm03", method = RequestMethod.GET)
	public String registerForm03(Member member) {
		log.info("registerForm03() 실행...!");
		return "home/formtag/registerForm01";
	}
	
	// @ModelAttribute 어노테이션으로 폼 객체의 속성명을 직접 지정할 수 있다.
	@RequestMapping(value="/registerForm04", method = RequestMethod.GET)
	public String registerForm04(@ModelAttribute("user") Member member) { // key를 user로 보냄
		log.info("registerForm04() 실행...!");
		return "home/formtag/registerForm01";
	}
	
	// 폼 객체의 프로퍼티에 값을 지정하여 모델을 통하여 전달한다.
	@RequestMapping(value="/registerForm05", method = RequestMethod.GET)
	public String registerForm05(Model model) {
		log.info("registerForm04() 실행...!");
		
		Member member = new Member();
		member.setUserId("hongkildong");
		member.setUserName("홍길동");
		
		model.addAttribute("user", member);
		return "home/formtag/registerForm01";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member, Model model) {
		log.info("register() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		
		model.addAttribute("member", member);
		return "home/formtag/result";
	}
}
