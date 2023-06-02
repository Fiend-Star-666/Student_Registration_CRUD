package com.training.java.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.java.entities.Student;
import com.training.java.repositories.StudentRepository;
import com.training.java.services.serviceImpl.DisplayTableImpl;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	DisplayTableImpl displayTableImpl;
	
	@GetMapping("/student")
	public String getAllStudentsPage(
	    @RequestParam(required = false) String string,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "3") int size,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "asc") String sortDirection,
	    Model model) {
	    try {
	        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
	        Pageable pagingSort = PageRequest.of(page, size, direction, sortBy);

	        Page<Student> pageStudents;

	        if (string == null) {
	            pageStudents = studentRepo.findAll(pagingSort);
	        } else {
	            pageStudents = studentRepo.findByNameIgnoreCaseContaining(string, pagingSort);
	        }

	        List<Student> students = pageStudents.getContent();

	        if (students.isEmpty()) {
	            model.addAttribute("errorMessage", "No students found.");
	            return "error-page";
	        }
	        
	        
	        Map<Student, String> formattedDates = displayTableImpl.formatDates(students);

	        model.addAttribute("formattedDates", formattedDates);
	        model.addAttribute("students", students);
	        model.addAttribute("currentPage", pageStudents.getNumber());
	        model.addAttribute("totalItems", pageStudents.getTotalElements());
	        model.addAttribute("totalPages", pageStudents.getTotalPages());
	        model.addAttribute("pageSize", size);
	        model.addAttribute("sortBy", sortBy);
	        model.addAttribute("sortDirection", sortDirection);

	        return "student-list";

	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "An error occurred while retrieving students: " + e.getMessage());
	        return "error-page";
	    }
	}

}
