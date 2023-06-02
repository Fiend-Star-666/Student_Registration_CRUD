package com.training.java.services;

import com.training.java.entities.Student;

import java.util.List;
import java.util.Map;

public interface DisplayTableService {
    List<Student> getAllStudents();
    Map<Student, String> formatDates(List<Student> students);
	Student getStudentById(int id);
	void deleteStudent(int id);
	void updateStudent(int id, Student updatedStudent);
}
