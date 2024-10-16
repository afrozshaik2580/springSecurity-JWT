package com.afroz.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afroz.app.entity.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	List<Student>students=new ArrayList<>(List.of(
			new Student(1, "afroz","java"),
			new Student(2, "babu", "spring")
			));

	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {
		
		return "hello " + request.getSession().getId();
	}
	
	@GetMapping("/about")
	public String about(HttpServletRequest request) {
		return "about " + request.getSession().getId();
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken token(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
	@PostMapping("/students")
	public void addstudent(@RequestBody Student student) {
		students.add(student);
	}
}
