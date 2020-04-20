package com.ust.ticl.springboot.pollingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ust.ticl.springboot.pollingsystem.service.PollingSystemService;
import com.ust.ticl.springboot.pollingsystem.web.bo.PollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.BaseResponse;


@RestController
@RequestMapping(name="/pollingsystem")
public class PollingSystemController {
	
	@Autowired
	private PollingSystemService pollingSystemService;

	
	@RequestMapping(value="/createPolling", method=RequestMethod.POST)
	public BaseResponse createPolling(@RequestBody PollingDetails pollingDetails, UriComponentsBuilder builder) {
				
				BaseResponse baseResponse = pollingSystemService.createPolling(pollingDetails); 
				return baseResponse;
	}
	/*
	
	@GetMapping("article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
		Article article = articleService.getArticleById(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@GetMapping("articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	@PostMapping("article")
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
                boolean flag = articleService.addArticle(article);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@DeleteMapping("article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} */
	/*public ResponseEntity<Object> createPolling(@Valid @RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}*/
/*	
	@RequestMapping("/updatePolling")
	public void updatePolling() {
		
	}
	
	@RequestMapping("/deletePolling")
	public void deletePolling() {
		
	}
	
	@RequestMapping("/performActualPolling")
	public void performActualPolling() {
		
	}
	
	@RequestMapping("/findPollingResult")
	public void findPollingResult() {
		
	}
	*/

}
