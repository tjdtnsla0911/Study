package com.aruerue.shop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aruerue.shop.model.Notice;
import com.aruerue.shop.repository.AddminNoticeRepository;

@Controller
public class AddminNoticeController {

	@Value("${file.path}")
	private String fileRealPath;

	private Notice notice;
	@Autowired
	AddminNoticeRepository addminNoticeRepository;
	@GetMapping("/noticelist")
	public String NoticeList(Model model) {
		System.out.println("노티스 리스트에왔습니까?");
		List<Notice> noticeList = addminNoticeRepository.findAllNotice();



		System.out.println("가져온 notice들은 ?="+noticeList);
		model.addAttribute("noticeList",noticeList);

		System.out.println("addminNotice에 보내기직전");
		return "addminNotice";
	}

	@PutMapping("/selectNotice/{id}")
	public @ResponseBody Notice selectNotice(@PathVariable int id,Model model) {
		System.out.println("왔음");
		System.out.println("받은 id="+id);
		//System.out.println("받은 id = ?"+id);


		Notice notice = addminNoticeRepository.findSelectnotice(id); //본문내용
		addminNoticeRepository.updateSelectNotice(notice);

		return notice;

	}
	@GetMapping("/selectRealNotice/{id}")
	public String selectRealNotice(@PathVariable int id,Model model, Model model2, Model model3) {
		System.out.println("노티스에옴");
		System.out.println("id = "+id);


		Notice notice = addminNoticeRepository.findSelectnotice(id);
		Notice noticUp = addminNoticeRepository.findUpnotice(id); //이후 적은 글
		List<Notice>noticDownfind = addminNoticeRepository.findDownnotice(id); //전에 적은 글
		System.out.println(noticUp);
		System.out.println(noticDownfind);
		int ddd =0;
		//배열을 돌려 현재숫자에 가장 가까운 숫자를구한다(낮은거)

		for(int i=0; i<=noticDownfind.size()-1;i++) {
			 ddd = noticDownfind.get(i).getId();

		}

		System.out.println("찾아온 가장 근사값이면서 낮은숫자  = "+ddd);
		Notice noticeDown = addminNoticeRepository.findSelectnotice(ddd); //이걸로 근사값찾음

	System.out.println("노티스다운  ="+noticeDown);

	String http = "http://localhost:8080";
	String http2 = http+notice.getThumb();
	notice.setThumb(http2);
		System.out.println("사진경로 = "+notice.getThumb());
		model.addAttribute("notice",notice); //현재페이지
		model2.addAttribute("noticeDown",noticeDown); //그전페이지
		model3.addAttribute("noticUp",noticUp); //그이후페이지
		return "addminSelectNotice";
	}


	@DeleteMapping("/deleteNotice/{id}")
	public @ResponseBody String deleteNotice(@PathVariable int id) {
		System.out.println("deleteNotice에옴");
		System.out.println("받은 id ?= "+id);

		addminNoticeRepository.deleteNotice(id);
		return "완료";

	}

	@PostMapping("/upNotice")
	public String upNotice(@RequestParam("thumb") MultipartFile thumb,String title,String content,String nullCheck) throws IOException {
		System.out.println("upNotice에 왔습니다");
		Notice notice = new Notice();
		System.out.println("thumb = "+thumb);
		System.out.println("사진의 이름 = "+thumb.getOriginalFilename());
		UUID uuid = UUID.randomUUID();
		System.out.println("thumb ="+thumb);
		System.out.println("title ="+title);
		System.out.println("content ="+content);
		notice.setTitle(title);

		notice.setContent(content);
		if(nullCheck!=null||nullCheck!="null") {
			System.out.println("널이아니다에 왔습니다");
			String uuidThumb = uuid+ "_"+thumb.getOriginalFilename();
			Path fileThumb = Paths.get(fileRealPath + uuidThumb);
			System.out.println("fileThumb = "+fileThumb);
			Files.write(fileThumb,thumb.getBytes());
			String realthumb = "/images/"+uuidThumb;
			System.out.println("realthumb = "+uuidThumb);
			notice.setThumb(realthumb);
		addminNoticeRepository.insertNotice(notice);
	System.out.println("null이아니다 다끝남^^");
		return "redirect:noticelist";
		}else {
			System.out.println("null이다에옴");
			addminNoticeRepository.insertNotice(notice);
			System.out.println("null이다 다끝남^^");

			return "redirect:noticelist";
		}


	}
	@PutMapping("/changeNotice/{id}")
	public @ResponseBody String changeNotice(
			MultipartFile thumb, String title, String nullCheck,String content
			, int id) throws IOException {
		Notice notice = addminNoticeRepository.findSelectnotice(id);
		System.out.println("꺼내온 notice = "+notice);
		System.out.println("changeNotice에왔습니다");
		System.out.println("받아온 thumb= "+thumb);
		System.out.println("받아온 title= "+title);
		System.out.println("받아온 nullCheck= "+nullCheck);
		System.out.println("받아온 content= "+content);
		System.out.println("받아온 id= "+id);
		notice.setTitle(title);
		notice.setContent(content);

		UUID uuid = UUID.randomUUID();
		if(nullCheck=="널아니다 ㅅㅂ") {//null이란소리
			System.out.println("널이다에왔습니다");

			System.out.println("들어가기전의 notice = "+notice);
			addminNoticeRepository.update(notice);
			return "완려";
		}else {
			System.out.println("널이 야니다에왔습니다");
			String uuidThumb= uuid+"_"+thumb.getOriginalFilename();
			Path fileThumb = Paths.get(fileRealPath+uuidThumb);
			Files.write(fileThumb,thumb.getBytes());
			String realThumb ="/images/"+uuidThumb;
			System.out.println("realThumb에 들어갈 thumb는? = "+realThumb);
			notice.setThumb(realThumb);
			addminNoticeRepository.update(notice);

		return "끝";
		}
	}


}