package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/jstl")
public class JSTLHomeController {
	
	/*
	 * 6. 코어 태그
	 */
	
	// 1) c:out value 속성에 지정한 값을 출력한다.
	@RequestMapping(value="/home0101", method = RequestMethod.GET)
	public String home0101(Model model) {
		Member member = new Member();
		member.setUserId("hongkd");
		model.addAttribute("member", member);
		return "home/jstl/home0101";
	}
	
	// 2) c:out escapeXml 속성의 기본값은 true이므로 특수문자를 변환한다.
	//	  escapeXml 속성값을 false로 지정하면 특수문자를 변환하지 않는다.(태그가 태그의 형태를 띄도록 한다)
	@RequestMapping(value="/home0102", method = RequestMethod.GET)
	public String home0102(Model model) {
		Member member = new Member();
		// 테스트1
//		member.setUserId("<p>hongkd<>&$0102</p>");
		// 테스트2
		member.setUserId("<script type='text/javascript'>alert(1);</script>");
		model.addAttribute("member", member);
		return "home/jstl/home0102";
	}
	
	// 3) value 속성에 지정한 값이 존재하지 않으면 default 속성에 지정한 값이 출력된다.
	@RequestMapping(value="/home0103", method = RequestMethod.GET)
	public String home0103(Model model) {
		Member member = new Member();
		member.setUserId(null);
		model.addAttribute("member", member);
		return "home/jstl/home0103";
	}
	
	// c:set
	// 1) JSP에서 사용될 변수를 설정하지 않고 자바빈즈 프로퍼티 값을 바로 표시한다.
	// 2) JSP에서 사용될 변수 memberId를 설정하여 사용한다. / 태그의 몸체를 값으로 사용한다.
	// 3) member 객체의 userId 프로퍼티 값을 활용하여 값을 설정한다.
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
		Member member = new Member();
		member.setUserId("hongkd0201");
		model.addAttribute("member", member);
		return "home/jstl/home0201";
	}
	
	// c:set 태그로 지정한 변수 memberId를 삭제한다.
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
		Member member = new Member();
		member.setUserId("hongkd0301");
		model.addAttribute("member", member);
		return "home/jstl/home0301";
	}
	
	// EL식 내부에 발생한 예외는 EL식 내부에서 처리하므로 catch 변수 'ex'에 저장되지 않는다.
	@RequestMapping(value="/home0401", method = RequestMethod.GET)
	public String home0401(Model model) {
		Member member = new Member();
		String[] hobbyArray = {"Music", "Movie"};
		member.setHobbyArray(hobbyArray);
		model.addAttribute("member", member);
		return "home/jstl/home0401";
	}
	
	// JSP 표현식에서 발생한 예외는 catch 변수 "ex"에 저장된다.
	@RequestMapping(value="/home0402", method = RequestMethod.GET)
	public String home0402(Model model) {
		return "home/jstl/home0402";
	}
	
	// 모든 c:when 태그의 test 결과값이 false이면 c:otherwise가 실행된다.
	@RequestMapping(value="/home0501", method = RequestMethod.GET)
	public String home0501(Model model) {
		Member member = new Member();
		member.setGender("M");
		model.addAttribute("member", member);
		return "home/jstl/home0501";
	}
	
	// 배열, List를 순차적으로 처리한다.(c:forEach)
	@RequestMapping(value="/home0601", method = RequestMethod.GET)
	public String home0601(Model model) {
		Member member = new Member();
		String[] hobbyArray = {"Music", "Movie"};
		member.setHobbyArray(hobbyArray);
		model.addAttribute("member", member);
		return "home/jstl/home0601";
	}
	
	// delims 속성에 지정된 구분자를 사용하여 items 속성에 전달된 문자열을 나누고 var 속성에 명시한 변수에 나뉘어진 문자열을 지정한다.
	@RequestMapping(value="/home0701", method = RequestMethod.GET)
	public String home0701(Model model) {
		Member member = new Member();
		String hobby = "Music, Movie";
		member.setHobby(hobby);
		model.addAttribute("member", member);
		return "home/jstl/home0701";
	}
	
	// c:import
	@RequestMapping(value="/home0801", method = RequestMethod.GET)
	public String home0801(Model model) {
		return "home/jstl/home0801";
	}
	
	// c:redirect
	// 지정한 페이지로 리다이렉트시킨다.
	@RequestMapping(value="/home0901", method = RequestMethod.GET)
	public String home0901(Model model) {
		return "home/jstl/home0901";
	}
}
