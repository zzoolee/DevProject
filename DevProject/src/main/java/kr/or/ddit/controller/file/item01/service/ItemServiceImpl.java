package kr.or.ddit.controller.file.item01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItemMapper;
import kr.or.ddit.vo.Item;

@Service
public class ItemServiceImpl implements IItemService {

	@Inject
	private ItemMapper mapper;
		
	@Override
	public void register(Item item) {
		mapper.create(item);
	}

	@Override
	public List<Item> list() {
		return mapper.list();
	}

	@Override
	public Item read(int itemId) {
		return mapper.read(itemId);
	}

	@Override
	public String getPicture(int itemId) {
		return mapper.getPicture(itemId);
	}

	@Override
	public void modify(Item item) {
		mapper.update(item);
	}

	@Override
	public void remove(int itemId) {
		mapper.delete(itemId);
	}

}
