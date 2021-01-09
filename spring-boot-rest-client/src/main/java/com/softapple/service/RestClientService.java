package com.softapple.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.softapple.dto.StudentDTO;
import com.softapple.url.ServiceUrl;

@Service
public class RestClientService {
	
	@Autowired
	private RestTemplate restTemplate;

	public List<StudentDTO> fetchAndReturnStudents(){
	//	ResponseEntity<StudentDTO[]> response = restTemplate.getForEntity("http://localhost:8080/student/find-all",	StudentDTO[].class);
		ResponseEntity<StudentDTO[]> response = restTemplate.exchange(ServiceUrl.STUDENT_ALL, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), StudentDTO[].class);
		return Arrays.asList(response.getBody());	
	}
	
	public String saveAndGetResponse(StudentDTO studentDTO){
		//	ResponseEntity<StudentDTO[]> response = restTemplate.getForEntity("http://localhost:8080/student/find-all",	StudentDTO[].class);
			
			ResponseEntity<String> response = restTemplate.exchange(ServiceUrl.STUDENT_SAVE, HttpMethod.POST, new HttpEntity<>(studentDTO), String.class);
			return response.getBody();	
	}
	
}
