package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess() 실행...!");
		User customUser = (User) authentication.getPrincipal();
		log.info("username : " + customUser.getUsername());
		log.info("password : " + customUser.getPassword()); // 보안 영역이라 값 안나온다
		// authentication: 로그인 성공시 만들어지는 객체
		// User: 아이디, 비밀번호, 역할 들어있다
		
		clearAuthenticationAttribute(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response); // 인터페이스로 임포트
//		String targetUrl = savedRequest.getRedirectUrl();
		// 타겟이 없을 때 홈으로 이동되는 처리
		String targetUrl = request.getContextPath() + "/";
		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		
		log.info("Login Success targetUrl : " + targetUrl);
		response.sendRedirect(targetUrl); // board/register로 이동
	}

	private void clearAuthenticationAttribute(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session == null) {
			return;
		}
		
		// SPRING_SECURITY_LAST_EXCEPTION 값
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
