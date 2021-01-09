package com.softapple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.softapple.dto.StudentDTO;
import com.softapple.service.RestClientService;

@RestController
@RequestMapping("/")
public class RestClientController {
	
	@Autowired
	private RestClientService restClientService;
	
	@PostMapping("/student/post")
	public ResponseEntity<?> postStudentStudent(@RequestBody StudentDTO studentDTO) {		
		return new ResponseEntity<> (restClientService.saveAndGetResponse(studentDTO), HttpStatus.CREATED);		
	}

	@GetMapping("/students/get")
	public ResponseEntity<?> getAllStudents() {		
		return new ResponseEntity<> (restClientService.fetchAndReturnStudents(), HttpStatus.FOUND);		
	}

}
