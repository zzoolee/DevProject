package kr.or.ddit.controller.file.item03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.file.item03.service.IItemService3;
import kr.or.ddit.vo.Item3;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item3")
public class FileUploadController03 {

	@Resource(name="uploadPath")
	private String resourcePath;
	
	@Inject
	private IItemService3 itemService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String item3RegisterForm() {
		log.info("item3RegisterForm() 실행...!");
		return "item3/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String item3Register(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for(int i = 0; i < files.length; i++) {
			log.info("files["+i+"] : " + files[i]);
		}
		
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다!");
		return "item3/success";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String item3List(Model model) {
		List<Item3> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "item3/list";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String item3Modify(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/modify";
	}
	
	@ResponseBody // return 값이 응답데이터라는 걸 알려주는 어노테이션 -> 없으면 경로로 인식!
	@RequestMapping(value="/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable("itemId") int itemId){
		log.info("itemId : " + itemId);
		// item_attach 테이블에서 fullname 추출
		// itemId 하나에 들어있는 파일들 (여러개가 들어있음)
		return itemService.getAttach(itemId);
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String item3Modify(Item3 item, Model model) {
		String files[] = item.getFiles();
		for(int i = 0; i < files.length; i++) {
			log.info("files["+i+"] : " + files[i]);
		}
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다!");
		return "item3/success";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.GET)
	public String item3RemoveForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/remove";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String item3Remove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg", "삭제가 완료되었습니다!");
		return "item3/success";
	}
	
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException{
		log.info("uploadAjax() 실행...!");
		
		log.info("originalName : " + file.getOriginalFilename());
		// 1) 원본 파일을 업로드
		// 2) s_원본파일 썸네일 파일 만들기
		// 3) /2023/09/05 폴더 경로 만들기(년/월/일로 폴더 경로 만들기)
		// 아래 나와있는 결과를 얻어온다. 
		// savedName은 /2023/09/04/UUID_원본파일명을 리턴한다.
		String savedName = UploadFileUtils.uploadFile(resourcePath, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		log.info("[displayFile]fileName : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(resourcePath + fileName); // 업로드 경로 + UUID파일명
			
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				// 파일 내보낼 때 필수로 지정해야 하는 헤더 정보
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + 
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
}
