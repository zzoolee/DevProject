package kr.or.ddit.controller.validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/validation")
public class ValidationController {
	/*
	 * 9장 입력 유효성 검증
	 * 1. 입력값 검증
	 */
	
	// Validation 테스트 할 폼 페이지 컨트롤러 메소드
	@RequestMapping(value="/registerValidationForm01", method = RequestMethod.GET)
	public String registerValidationForm01(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm01";
	}
	
	// validation 처리
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerValidatedForm01Process(@Validated Member member, BindingResult result) {
		// @Validated에 의해 Member 객체에 대한 입력값 검증, 에러 발생시 BindingResult에 에러 정보
		log.info("registerValidatedForm01Process 실행...!");
		
		if(result.hasErrors()) { // 입력값 검증 실패로 다시 해당 페이지 돌아감
			return "validation/registerValidationForm01";
		}
		
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		log.info("userName : " + member.getUserName());
		log.info("email : " + member.getEmail());
		log.info("gender : " + member.getGender());
		
		return "validation/success";
	}
	
	/*
	 * 2. 입력값 검증 결과
	 */
	
	// 1) 입력값 검증 대상의 도메인 클래스 직후에 BindingResult를 정의하여 에러 정보를 확인할 수 있다.(확인은 처리 메서드에서)
	@RequestMapping(value="/registerValidationForm02", method = RequestMethod.GET)
	public String registerValidationForm02(Model model) {
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm02";
	}
	
	@RequestMapping(value="/result2", method = RequestMethod.POST)
	public String registerValidationForm02Process(@Validated Member member, BindingResult result) {
		log.info("registerValidationForm02Process() 실행...!");
		// 입력값 검증 에러가 발생한 경우 true를 반환합니다.
		log.info("result.hasErrors() : " + result.hasErrors());
	
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			
			log.info("allErrors.size() : " + allErrors.size());
			log.info("globalErrors.size() : " + globalErrors.size());
			log.info("fieldErrors.size() : " + fieldErrors.size());
			
			for(int i = 0; i < allErrors.size(); i++) {
				ObjectError objError = allErrors.get(i);
				log.info("allError : " + objError);
			}
			for(int i = 0; i < globalErrors.size(); i++) {
				ObjectError objError = globalErrors.get(i);
				log.info("globalErrors : " + objError);
			}
			for(int i = 0; i < fieldErrors.size(); i++) {
				ObjectError fieldError = fieldErrors.get(i);
				log.info("fieldErrors : " + fieldError);
				log.info("fieldError.getDefaultMessage() : " + fieldError.getDefaultMessage());
			}
			
			return "validation/registerValidationForm02";
		}
		
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		log.info("Email : " + member.getEmail());
		log.info("Gender : " + member.getGender());
		
		return "validation/success";
	}
	
	/*
	 * 3. 입력값 검증 규칙
	 */
	
	/*
	 * 4. 중첩된 자바빈즈 입력값 검증
	 */
	
	@RequestMapping(value="/registerValidationForm03", method = RequestMethod.GET)
	public String registerValidation03(Model model) {
		log.info("registerValidation03() 실행...!");
		model.addAttribute("member", new Member());
		return "validation/registerValidationForm03";
	}
	
	@RequestMapping(value="/result3", method = RequestMethod.POST)
	public String registerFormValidationResult3(@Validated Member member, BindingResult result) {
		log.info("registerFormValidationResult3() 실행...!");
	
		if(result.hasErrors()) {
			return "validation/registerValidationForm03";
		}
		
		return "validation/success";
	}
}
