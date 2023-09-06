package kr.or.ddit.controller.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/validation")
public class JSPFormValidationController {
	/*
	 * 15. 입력값 검증 에러
	 * - 입력값 검증 에러를 출력하려면 <form:erros> 요소를 사용한다.
	 */
	
	// 클라이언트에서 서버로 modelAttribute 에서 바인딩 할 객체를 설정하고 아이디를 누락시킨 후 서버로 요청했을 때,
	// form:errors에 담길 메세지는 바인딩되지만, 바인딩됐다고 출력을 위한 validation 역할자가 없어서 없는 채로 결과 페이지가 출력된다.
	// 메시지 출력을 도울 누군가(조력자)가 필요한 상황이다!!
	@RequestMapping(value="/registerFormValidation01", method = RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01 실행...!");
		
		Member member = new Member();
		member.setUserId("hongkildong123");
		member.setUserName("홍길동123");
		member.setEmail("aaa@ccc.com");
		model.addAttribute("member", member);
		return "form/validation/registerFormValidation01";
	}
}
