package kr.or.ddit.vo;

import lombok.Data;

@Data
public class Item3 {
	private int itemId;
	private String itemName;
	private int price;
	private String description;
	private String[] files; // hidden 태그 취합해서 넘겨받을 파라미터
}
