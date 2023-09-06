package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotBlank(message = "입력이 누락되었습니다!") // 문자열이 null이 아니로 trim한 길이가 0보다 크다는 것을 검사
	private String userId = "a001";
	@NotBlank // 문자열이 null이 아니로 trim한 길이가 0보다 크다는 것을 검사
	@Size(max=3)
	private String userName = "hongkd";
	@NotBlank // 문자열이 null이 아니로 trim한 길이가 0보다 크다는 것을 검사
	private String password = "1234";
	private int coin = 100;
	@Past // 과거 날짜인지를 검사한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Email
	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreigner;
	private String developer;
	private String nationality;
	
	@Valid // 중첩된 자바빈즈의 입력값 검증을 지정한다.
	private Address address;
	@Valid // 자바빈즈 컬렉션의 입력값 검증을 지정한다.
	private List<Card> cardList;
	
	private String cars;
	private String[] carArray;
	private List<String> carList;
	
	private String introduction;
	private String birthDay;
}
