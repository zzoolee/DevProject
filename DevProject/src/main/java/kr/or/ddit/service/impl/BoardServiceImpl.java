package kr.or.ddit.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BoardMapper;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements IBoardService{

	@Inject
	private BoardMapper mapper;
		
	@Override
	public void register(Board board) {
		log.info("[serviceImpl] register");
		mapper.create(board);
	}

	@Override
	public List<Board> list() {
		log.info("[serviceImpl] list");
		return mapper.list();
	}

	@Override
	public Board read(int boardNo) {
		log.info("[serviceImpl] read");
		return mapper.read(boardNo);
	}

	@Override
	public void modify(Board board) {
		log.info("[serviceImpl] modify");
		mapper.update(board);
	}

	@Override
	public void delete(int boardNo) {
		log.info("[serviceImpl] delete");
		mapper.delete(boardNo);
	}

	@Override
	public List<Board> search(Board board) {
		log.info("[serviceImpl] search");
		return mapper.search(board);
	}

}
