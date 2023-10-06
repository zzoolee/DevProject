package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {

	public DDITMemberVO loginCheck(DDITMemberVO member);
	public DDITMemberVO idCheck(String memId);
	public int signup(DDITMemberVO memberVO);
	public String findId(DDITMemberVO memberVO);
	public String findPw(DDITMemberVO memberVO);
	public DDITMemberVO readByUserId(String username);
	public void signupAuth(int memNo);

}
