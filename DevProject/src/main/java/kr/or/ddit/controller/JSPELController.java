package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jspel")
public class JSPELController {
	
	@RequestMapping(value="/home0101", method = RequestMethod.GET)
	public String home0101(Model model) {
		String str = "<font>Hello World!</font>";
		model.addAttribute("str", str);
		return "home/el/home0101";
	}
	
}
