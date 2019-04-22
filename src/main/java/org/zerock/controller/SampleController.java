package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample/*")
public class SampleController {

	@RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basic() {
		log.info("basic ..........................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get ..........................");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		/* ~/sample/ex01?name=AAA&age=10 */
		log.info(dto);				// SampleDTO(name=AAA, age=10)
		
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		/* ~/sample/ex02?name=AAA&age=10 */
		log.info("name: " +  name);	// name: AAA
		log.info("age: " +  age);	// age: 10
		
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		/* ~/sample/ex02List?ids=111&ids=222&ids=333 */
		log.info("ids: " +  ids);	// ids: [111, 222, 333]
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		/* ~/sample/ex02Array?ids=111&ids=222&ids=333 */
		log.info("array ids: " +  Arrays.toString(ids));// array ids: [111, 222, 333]
		
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		/* 방법1 : 
		 * ~ /sample/ex02Bean?list[0].name=AAA&list[1].name=BBB&list[2].name=CCC   
		 */
		
		/* 방법2 : '[' => '%5B'로, ']' => '%5D'로 변경한다. 
		 * ~ /sample/ex02Bean?list%5B0%5D.name=AAA&list%5B1%5D.name=BBB&list%5B2%5D.name=CCC
		 */
		
		/* 방법3 : JavaScript를 이용하는 경우에는 encodeURIComponent()로 해결 가능 */
		
		log.info("list dtos: " + list);	
		// list dtos: SampleDTOList(list=[SampleDTO(name=AAA, age=0), SampleDTO(name=BBB, age=0), SampleDTO(name=CCC, age=0)]) 
		
		return "ex02Bean";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		/* ~ /sample/ex03?title=test&dueDate=2019-04-22 */
		log.info("todo: " + todo);	// todo: TodoDTO(title=test, dueDate=Mon Apr 22 00:00:00 KST 2019)
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		/* ~ /sample/ex04?name=AAA&age=11&page=9 */
		log.info("dto: " + dto);	// dto: SampleDTO(name=AAA, age=11)
		
		// @ModelAttribute가 없으면 화면으로 전달되지 않는다.
		log.info("page: " + page);	// page: 9
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05 .......");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06 .......");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07 .......");
		
		// {"name": "홍길동"}
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
}
