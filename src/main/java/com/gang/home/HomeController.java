package com.gang.home;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

//import com.gang.home.board.qna.QnaMapper;
import com.gang.home.board.qna.QnaVO;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
//	private String message;
//	@Value("${my.default}")
//	private String app;
	
	//private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
//	@Autowired
//	private QnaMapper qnaMapper;
//	
	@GetMapping("/")
	public String home() throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		
		//1.Header
		HttpHeaders headers = new HttpHeaders();
		//header :  key:value
		//headers.add("key","value");
		//headers.set헤더명("값");
		
		//2.parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		
		//3.요청 정보 객체(1,2 번을 모음)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params,headers);
		
		//4.전송 후 결과
		ResponseEntity<String> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", String.class, request);
		String result = response.getBody();
		log.info("response : {}",response);
		
		//log.info("message : {}",message);
	//	log.info("default : {}",app);

		return "index";
	}
}
