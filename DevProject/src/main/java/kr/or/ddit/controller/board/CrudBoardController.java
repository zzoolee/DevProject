package kr.or.ddit.controller.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/board")
public class CrudBoardController {
	
	// IBoardService 구현체 객체를 사용하기 위한 의존성 주입
	@Inject
	private IBoardService service;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String crudRegisterForm() {
		log.info("crudRegisterForm() 실행...!");
		return "crud/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String crudRegister(Board board, Model model) {
		log.info("crudRegister() 실행...!");
		// 1. 서비스로 등록 기능을 요청
		service.register(board);
		// 2. 등록완료 메세지를 완료 페이지로 전달 및 이동
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "crud/success";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String crudList(Model model) {
		log.info("crudList() 실행...!");
		List<Board> boardList = service.list();
		model.addAttribute("list", boardList);
		return "crud/list";
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String crudRead(int boardNo, Model model) {
		log.info("crudRead() 실행...!");
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		return "crud/read";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String crudModifyForm(int boardNo, Model model) {
		log.info("crudModifyForm() 실행...!");
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("status", "u"); // 수정임을 나타낸다
		return "crud/register";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String crudModify(Board board, Model model) {
		log.info("crudModify() 실행...!");
		service.modify(board);
		model.addAttribute("msg", "수정이 완료되었습니다");
		return "crud/success";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String crudDelete(int boardNo, Model model) {
		log.info("crudDelete() 실행...!");
		service.delete(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다");
		return "crud/success";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String crudSearch(String title, Model model) {
		log.info("crudSearch() 실행...!");
		
		Board board = new Board();
		board.setTitle(title);
		
		List<Board> boardList = service.search(board);
		
		model.addAttribute("list", boardList); // 검색 결과가 들어있다
		model.addAttribute("board", board); // 검색 키워드가 들어있다
		return "crud/list";
	}
}
