package kr.or.ddit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
   /*
    * 5장 컨트롤러 요청 처리
    * 
    * 1. 컨트롤러 메서드 매개변수
    * 
    * 		Model
    * 		- 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
    * 		RedirectAttributes
    * 		- 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
    * 		자바빈즈 클래스
    * 		- 요청 파라미터를 가지고 있는 자바빈즈 클래스
    * 		MultipartFile
    * 		- 멀티파트 요청을 사용해 업로드된 파일 정보를 가지고 있는 인터페이스
    * 		BindResult
    * 		- 도메인 클래스의 입력값 검증을 가지고 있는 인터페이스
    * 		java.security.Principal
    * 		- 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
    */
	
	/*
	 * 5장 컨트롤러 요청 처리 시작 컨트롤러 메소드
	 * - 페이지를 요청해 테스트 진행한다.
	 */
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerForm";
	}
	
	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		// userId가 기본적인 파라미터 형태로 받게 될 경우에는 null로 들어온다.
		// 경로상에 포함되어 있는 값은 @PathVariable을 꼭 붙여주어야 값을 바인딩할 수 있다.
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	// 3) HTML Form 필드가 여러개일 경우에도 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String registe01(String userId, String password) {
		log.info("registe01() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 4) HTML 폼 필드가 여러 개일 경우에 컨트롤러 매개변수의 위치는 상관없다.
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String registe02(String password, String userId, int coin) {
		log.info("registe02() 실행...!");
		log.info("password : " + password);
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	// 5) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 문자열이면 그대로 문자열 형태로 요청 데이터를 취득한다.
	@RequestMapping(value="/register03", method = RequestMethod.POST)
	public String register03(String userId, String password, String coin) {
		log.info("registe03() 실행...!");
		log.info("password : " + password);
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	/*
	 * 3. 요청 데이터 처리 어노테이션
	 * 
	 * 		@PathVariable
	 * 		- URL에서 경로 변수 값을 가져오기 위한 어노테이션
	 * 		@RequestParam
	 * 		- 요청 파라미터 값을 가져오기 위한 어노테이션
	 * 		@RequestHeader
	 * 		- 요청 헤더 값을 가져오기 위한 어노테이션
	 * 		@RequestBody
	 * 		- 요청 본문 내용을 가져오기 위한 어노테이션
	 * 		@CookieValue
	 * 		- 쿠키 값을 가져오기 위한 어노테이션
	 */
	
	// 1) URL 경로 상의 경로 변수가 여러 개일 경우, @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다.
	@RequestMapping(value="/register/{userId}/{coin}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId, @PathVariable int coin) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	// 2) HTML Form 필드명과 컨트롤러 매개변수명이 일치(대소문자 구분)하지 않으면 요청을 처리할 수 없다.
	@RequestMapping(value="/register0201", method = RequestMethod.POST)
	public String register0201(String username) {
		log.info("register0201() 실행...!");
		log.info("userId : " + username); // null
		return "success";
	}
	
	// 3) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.
	@RequestMapping(value="/register0202", method = RequestMethod.POST)
	public String register0202(@RequestParam("userId") String username) {
		log.info("register0202() 실행...!");
		log.info("username : " + username);
		return "success";
	}
	
	/*
	 * 4. 요청 처리 자바빈즈
	 */
	
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method = RequestMethod.POST)
	public String registerJavaBeans01(Member member) {
		log.info("registerJavaBeans01() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin());
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개변수로 처리한다.
	@RequestMapping(value="/beans/register02", method = RequestMethod.POST)
	public String registerJavaBeans02(Member member, int coin) {
		log.info("registerJavaBeans02() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin()); // 100
		log.info("coin : " + coin); // 100 -> 같은 이름이면 무조건 바인딩! 즉 두 개의 변수에 모두 바인딩
		return "success";
	}
	
	/*
	 * 5. Date 타입 처리
	 * - 스프링 MVC Date 타입의 데이터를 처리하는 여러 방법을 제공한다.
	 * - 따로 지정하지 않으면 변환에 적합한 날짜 문자열 형식은 yyyy/MM/dd이다.
	 */
	
	@RequestMapping(value="/registerByGet01", method = RequestMethod.GET)
	public String registerByGet01(String userId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
		log.info("registerByGet01() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	@RequestMapping(value="/registerByGet02", method = RequestMethod.GET)
	public String registerByGet02(Member member) {
		log.info("registerByGet02() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getDateOfBirth() : " + member.getDateOfBirth());
		return "success";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member) {
		log.info("register() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getDateOfBirth() : " + member.getDateOfBirth());
		return "success";
	}
	
	/*
	 * 6. @DateTimeFormat 어노테이션
	 * - @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜형식을 지정할 수 있다.
	 * 
	 * 테스트는 /registerByGet02를 요청하고 파라미터로 받아줄, Member 클래스의 날짜를 받는 멤버 변수에 어노테이션을 설정합니다.
	 */
	
	/*
	 * 7. 폼 방식 요청 처리
	 */
	
	// 1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerUserId", method = RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerMemberUserId", method = RequestMethod.POST)
	public String registerMemberUserId(Member member) {
		log.info("registerMemberUserId() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		return "success";
	}
	
	// 3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerPassword", method = RequestMethod.POST)
	public String registerPassword(String password) {
		log.info("registerPassword() 실행...!");
		log.info("password : " + password);
		return "success";
	}
	
	// 4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerRadio", method = RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행...!");
		log.info("gender : " + gender);
		return "success";
	}
	
	// 5) 폼 셀렉트박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method = RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality : " + nationality);
		return "success";
	}
	
	// 6) 복수 선택이 가능한 폼 셀렉트박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect01", method = RequestMethod.POST)
	public String registerMultipleSelect01(String cars) {
		log.info("registerMultipleSelect01() 실행...!");
		log.info("cars : " + cars); // Jeep,Bmw,Audi,Volvo
		return "success";
	}
	
	// 7) 복수 선택이 가능한 폼 셀렉트박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect02", method = RequestMethod.POST)
	public String registerMultipleSelect02(String[] carArray) {
		log.info("registerMultipleSelect02() 실행...!");
		if(carArray != null) {
			log.info("carArray.length : " + carArray.length);
			for(int i = 0; i < carArray.length; i++) {
				log.info("carArray["+i+"] : " + carArray[i]);
			}
		}else {
			log.info("carArray == null");
		}
		return "success";
	}
	
	// 8) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultipleSelect03", method = RequestMethod.POST)
	public String registerMultipleSelect03(ArrayList<String> carList) {
		// 리스트로는 셀렉트 박스 값을 가져올 수 없다.
		// 배열 형태를 이용하여 받아오거나 객체로 받아온다.
		log.info("registerMultipleSelect03() 실행...!");
		if(carList != null && carList.size() > 0) {
			log.info("carList.size() : " + carList.size());
			for(int i = 0; i < carList.size(); i++) {
				log.info("carList["+i+"] : " + carList.get(i));
			}
		}else {
			log.info("carList == null");
		}
		return "success";
	}
	
	// 9) 폼 체크박스 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox01", method = RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby : " + hobby); // Sports,Music,Movie
		return "success";
	}
	
	// 10) 폼 체크박스 요소 값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox02", method = RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerMultipleSelect02() 실행...!");
		if(hobbyArray != null) {
			log.info("hobbyArray.length : " + hobbyArray.length);
			for(int i = 0; i < hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		}else {
			log.info("hobbyArray == null");
		}
		return "success";
	}
	
	// 11) 폼 체크박스 요소 값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox03", method = RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		// 받는 타입을 List하게 되면 [No primary or default constructor found for interface java.util.List] 에러 발생
		// List로는 데이터를 받는게 불가능하다!
		// 리스트와 같은 형태의 값을 받으려면 String[] 또는 String 기본 타입으로 데이터를 받아온다.
		log.info("registerCheckbox03() 실행...!");
		if(hobbyList != null && hobbyList.size() > 0) {
			log.info("hobbyList.size() : " + hobbyList.size());
			for(int i = 0; i < hobbyList.size(); i++) {
				log.info("hobbyList["+i+"] : " + hobbyList.get(i));
			}
		}else {
			log.info("hobbyList == null");
		}
		return "success";
	}
	
	// 12) 폼 체크박스 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox04", method = RequestMethod.POST)
	public String registerCheckbox04(String developer) {
		log.info("registerCheckbox04() 실행...!");
		log.info("developer : " + developer); // Y or null
		return "success";			
	}
	
	// 13) 폼 체크박스 요소 값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox05", method = RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner) {
		log.info("registerCheckbox05() 실행...!");
		log.info("foreigner : " + foreigner); // true or false
		return "success";			
	}
	
	// 14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerAddress", method = RequestMethod.POST)
	public String registerAddress(Address address) {
		log.info("registerAddress() 실행...!");
		if(address != null) {
			log.info("address.getPostCode() : " + address.getPostCode());
			log.info("address.getLocation() : " + address.getLocation());
		}else {
			log.info("address == null");
		}
		return "success";
	}
	
	// 15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.(name 설정에 유의)
	@RequestMapping(value="/registerUserAddress", method = RequestMethod.POST)
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress() 실행...!");
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("address.getPostCode() : " + address.getPostCode());
			log.info("address.getLocation() : " + address.getLocation());
		}else {
			log.info("address == null");
		}
		
		return "success";
	}
	
	// 16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserCardList", method = RequestMethod.POST)
	public String registerUserCardList(Member member) {
		log.info("registerUserCardList() 실행...!");
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null) {
			log.info("cardList.size() : " + cardList.size());
		
			for(int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("card.getNo() : " + card.getNo());
				log.info("card.getValidMonth() : " + card.getValidMonth());
			}
		}else {
			log.info("cardList == null");
		}
		
		return "success";
	}
	
	// 17) 폼 텍스트 영역 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerTextArea", method = RequestMethod.POST)
	public String registerTextArea(String introduction) {
		log.info("registerTextArea() 실행...!");
		log.info("introduction : " + introduction);
		return "success";
	}
	
	// 18) 폼 텍스트 영역 요소값을 Date 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerDate01", method = RequestMethod.POST)
	public String registerDate01(Date dateOfBirth) {
		// yyyy/MM/dd 형식으로 데이터를 전송해야한다.
		log.info("registerDate01() 실행...!");
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	// 19) 폼 텍스트 영역 요소값을 @DateTimeFormat 어노테이션을 지정하여 Date 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerDate02", method = RequestMethod.POST)
	public String registerDate02(@DateTimeFormat(pattern = "yyyyMMdd") Date dateOfBirth) {
		log.info("registerDate02() 실행...!");
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	
	
	/*
	 * 폼 데이터 양식을 활용한 전체 문제
	 * - 아래 작성한 메소드가 시작페이지
	 */
	@RequestMapping(value="/registerAllForm", method = RequestMethod.GET)
	public String registerAllForm() {
		log.info("registerAllForm() 실행...!");
		return "member/registerAllForm";
	}
	
	// 아래 메소드를 통해 registerAllForm에서 입력 후 넘긴 데이터를 출력 하고 가용해주세요.
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(Member member, Model model) {
		// registerAllForm 페이지에서 넘겨받은 회원 데이터를 log.info() 메소드를 통해서 아래와 같이 출력하고 
		// registerAllResult.jsp 페이지로 넘겨 출력해주세요.   
		// (registerAllResult.jsp페이지로 넘어가 작성된 주석에 따라 문제를 풀어주세요.)
		
		// [출력 예시]
		// 유저 ID : ddit_june
		// 패스워드 : 1234
		// 이름 : 조현준
		// E-Mail : wh-guswns123@hanmail.net
		// 생년월일 : 2023년 08월 25일 (여기서는 Date 타입으로 넘긴 데이터 그대로를 출력해주세요.)
		// 성별 : 남자
		// 개발자여부 : 개발자
		// 외국인여부 : 외국인
		// 국적 : 대한민국
		// 소유차량 : 소유차량 없음
		// 취미 : 운동 영화시청
		// 우편번호 : 45617
		// 주소 : 대전광역시 중구 오류동
		// 카드1(번호) : 1234-1234-1234-1234
		// 카드1(유효년월) : 2023년 08월 25일 (여기서는 Date 타입으로 넘긴 데이터 그대로를 출력해주세요.)
		// 카드2(번호) : 4576-4576-4567-4567
		// 카드2(유효년월) : 2023년 08월 26일 (여기서는 Date 타입으로 넘긴 데이터 그대로를 출력해주세요.)
		// 소개 : 반갑습니다!
		// 
		// ** 조건
		// - 선택한 성별에 따라 남자/여자/기타로 나타내주세요.
		// - 개발자 여부 체크에 따라 개발자/일반 으로 나타내주세요.
		// - 외국인 여부 체크에 따라 외국인/내국인으로 나타내주세요.
		// - 국적에 따라 대한민국/독일/호주/캐나다로 나타내주세요.
		// - 소유차량 선택에 따라 소유차량 없음/JEEP/VOLVO/BMW/AUDI 로 나타내주세요.
		// 	> 선택 갯수에 따라 아래처럼 출력해주세요.
		//	> 소유차량 없음
		//	> JEEP
		//	> JEEP VOLVO AUDI
		// - 취미 선택에 따라 운동/영화시청/음악감상 으로 나타내주세요.
		//	> 선택 갯수에 따라 아래처럼 출력해주세요.
		//	> 취미 없음
		//	> 음악감상
		//	> 운동 영화시청
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");		
		
		log.info("유저 ID : " + member.getUserId());
		log.info("패스워드 : " + member.getPassword());
		log.info("이름 : " + member.getUserName());
		log.info("E-Mail : " + member.getEmail());
		
		String birth = dateFormat.format(member.getDateOfBirth());
		log.info("생년월일 : " + birth);
		
		switch(member.getGender()) {
			case "male" : member.setGender("남자"); break;
			case "female" : member.setGender("여자"); break;
			case "other" : member.setGender("기타"); break;
		}
		log.info("성별 : " + member.getGender());
		
		if(member.getDeveloper() == null) {
			member.setDeveloper("일반");
		}else {
			member.setDeveloper("개발자");
		}
		log.info("개발자여부 : " + member.getDeveloper());
		
		if(member.isForeigner()) {
			log.info("외국인여부 : 외국인");
		} else {
			log.info("외국인여부 : 내국인");
		}
		
		switch(member.getNationality()) {
			case "Korea" : member.setNationality("대한민국"); break;
			case "Germany" : member.setNationality("독일"); break;
			case "Austrailia" : member.setNationality("호주"); break;
			case "Canada" : member.setNationality("캐나다"); break;
		}
		log.info("국적 : " + member.getNationality());
		
		if(member.getCars() == "") {
			member.setCars("소유차량 없음");
		} else {
			member.setCars(member.getCars().replaceAll(",", " "));
		}
		log.info("소유차량 : " + member.getCars());
		
		if(member.getHobby() == null) {
			member.setHobby("취미 없음");
		}else {
			member.setHobby(member.getHobby().replaceAll(",", " "));
			member.setHobby(member.getHobby().replace("Sports", "운동"));
			member.setHobby(member.getHobby().replace("Music", "음악감상"));
			member.setHobby(member.getHobby().replace("Movie", "영화시청"));
		}
		log.info("취미 : " + member.getHobby());
		log.info("우편번호 : " + member.getAddress().getPostCode());
		log.info("주소 : " + member.getAddress().getLocation());
		
		List<Card> cardList = member.getCardList();
		for(int i=0; i<cardList.size(); i++) {
			Card card = cardList.get(i);
			log.info("카드"+(i+1)+"(번호) : " + card.getNo());
			String month = dateFormat.format(card.getValidMonth());
			log.info("카드"+(i+1)+"(유효년월) : " + month);
		}
	
		log.info("소개 : " + member.getIntroduction());
		
		model.addAttribute("member", member);
		return "member/registerAllResult";
	}
	
	/*
	 * 8. 파일 업로드 폼 방식 요청 처리
	 * - 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 *  > pom.xml 내, commons-fileupload / commons-io 라이브러리 의존 관계 등록
	 *  > web.xml에 모든 경로에 대해서 MultipartFilter를 등록
	 *  
	 *  ## 위 설정을 진행하였는데도 에러가 나는 경우 조치방법
	 *  
	 *  파일 업로드가 에러 나는 경우 조치 방법이니 꼭 필히! 필수로! 제발!! 조치하세요
	 *  0) 클린! 클린!!! 클린!!!!!!! 진짜 클린!!!! 클린!!!!!!!(클린 후 서버 path 확인)
	 *  1) 파일 업로드에 필요한 라이브러리가 추가되었는지 pom.xml에서 확인(dependency) + maven jar 파일 추가 확인
	 *  2) web.xml에서 MultipartFilter 추가되었는지 확인(filter)
	 *  3) servers > context.xml 수정
	 *	 > Context 태그 내 allowCasualMultipartParsing="true" path="/" 속성들을 추가한다.
	 */
	
	// 1) 파일업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile01", method = RequestMethod.POST)
	public String registerFile01(MultipartFile picture) {
		log.info("registerFile01() 실행...!");
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 2) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 문자열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile02", method = RequestMethod.POST)
	public String registerFile02(String userId, String password, MultipartFile picture) {
		log.info("registerFile02() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile03", method = RequestMethod.POST)
	public String registerFile03(Member member, MultipartFile picture) {
		log.info("registerFile02() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 4) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile04", method = RequestMethod.POST)
	public String registerFile04(FileMember fileMember) {
		log.info("registerFile04() 실행...!");
		
		log.info("fileMember.getUserId() : " + fileMember.getUserId());
		log.info("fileMember.getPassword() : " + fileMember.getPassword());
		
		MultipartFile picture = fileMember.getPicture();
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 5) 여러개의 파일업로드 폼 파일 요소값을 여러개의 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile05", method = RequestMethod.POST)
	public String registerFile05(Member member, MultipartFile picture, MultipartFile picture2) {
		log.info("registerFile05() 실행...!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName2 : " + picture2.getOriginalFilename());
		log.info("size2 : " + picture2.getSize());
		log.info("contentType2 : " + picture2.getContentType());
		return "success";
	}
	
	// 6) 여러개의 파일업로드 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerFile06", method = RequestMethod.POST)
	public String registerFile06(List<MultipartFile> pictureList) {
		// 컬렉션 List로는 이미지 파일을 가져올 수 없다.
		log.info("registerFile06() 실행...!");
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName["+i+"] : " + picture.getOriginalFilename());
			log.info("size["+i+"] : " + picture.getSize());
			log.info("contentType["+i+"] : " + picture.getContentType());
		}		
		return "success";
	}
	
	// 7) 여러 개의 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile07", method = RequestMethod.POST)
	public String registerFile07(MultiFileMember multiFileMember) {
		// 자바빈즈 객체 안에 멤버변수로 선언되어 있는 리스트로는 받을 수 있다.
		log.info("registerFile07() 실행...!");
		
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
			
		log.info("pictureList.size : " + pictureList.size());
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName["+i+"] : " + picture.getOriginalFilename());
			log.info("size["+i+"] : " + picture.getSize());
			log.info("contentType["+i+"] : " + picture.getContentType());
		}
		
		return "success";
	}
	
	// 8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile08", method = RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureList) {
		log.info("registerFile08() 실행...!");
		log.info("pictureList.length : " + pictureList.length);
	
		for(MultipartFile picture : pictureList) {
			log.info("originalName: " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		return "success";
	}
	
}
