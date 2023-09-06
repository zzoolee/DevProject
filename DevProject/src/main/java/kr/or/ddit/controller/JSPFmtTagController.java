package kr.or.ddit.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/fmttag")
public class JSPFmtTagController {
	/*
	 * 7. 숫자 및 날짜 포맷팅 처리 태그
	 */
	
	// 1) type 속성을 지정하거나 pattern 속성을 지정하여 숫자를 형식화한다.
	@RequestMapping(value="/home0101", method = RequestMethod.GET)
	public String home0101(Model model) {
		int coin = 100;
		model.addAttribute("coin", coin);
		return "home/fmttag/home0101";
	}
	
	// 2) type 속성이 지정되지 않으면 기본값은 number이다.
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
//		int coin = 1000;
//		String coin = "￦1000";
		String coin = "1000%";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0201";
	}
	
	// 3) pattern 속성을 사용하여 직접 사용할 서식을 지정한다.
	@RequestMapping(value="/home0202", method = RequestMethod.GET)
	public String home0202(Model model) {
		String coin = "1,000.25";
		model.addAttribute("coin", coin);
		return "home/fmttag/home0202";
	}
	
	// 4) type 속성을 date로 지정하여 날짜 포맷팅을 한다.
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0301";
	}
	
	// 4) type 속성을 time로 지정하여 날짜 포맷팅을 한다.
	@RequestMapping(value="/home0302", method = RequestMethod.GET)
	public String home0302(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0302";
	}
	
	// 5) type 속성을 both로 지정하여 날짜 및 시간 포맷팅을 한다.
	@RequestMapping(value="/home0303", method = RequestMethod.GET)
	public String home0303(Model model) {
		Date date = new Date();
		model.addAttribute("now", date);
		return "home/fmttag/home0303";
	}
	
	// 6) dateStyle 속성을 short로 지정하여 문자열을 Date 객체로 변환한다.
	@RequestMapping(value="/home0401", method = RequestMethod.GET)
	public String home0401(Model model) {
		String dateValue = "20. 2. 1"; // dateStyle short에 해당
		model.addAttribute("dateValue", dateValue);
		return "home/fmttag/home0401";
	}
	
	@RequestMapping(value="/home0402", method = RequestMethod.GET)
	public String home0402(Model model) {
		String dateValue = "2019년 2월 1일 (금)"; // dateStyle long에 해당
		model.addAttribute("dateValue", dateValue);
		return "home/fmttag/home0402";
	}
}
