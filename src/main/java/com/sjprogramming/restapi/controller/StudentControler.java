package com.sjprogramming.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sjprogramming.restapi.entity.Student;
import com.sjprogramming.restapi.repository.StudentRepository;

@RestController
public class StudentControler {
	
	@Autowired
	StudentRepository repo;

	//get all the students
	//localhost:8080/students

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		
		List<Student> students=repo.findAll();
		
		return students;
	}
	
	//localhost:8080/students/1
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {

		Student student = repo.findById(id).get();

		return student;
	}

	@PostMapping("/students/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student) {
		repo.save(student);
		
	}

	@PutMapping("/students/update/{id}")
	public Student updateStudents(@PathVariable int id) 
	{
		Student student=repo.findById(id).get();
		student.setName("poonam");
		student.setPercentage(75);
		repo.save(student);
		
		return student;
	}
	
	@DeleteMapping("/students/delete/{id}")
	public void removeStudent(@PathVariable int id) {
		Student student=repo.findById(id).get();
		repo.delete(student);
	}
}
