package com.training.java.services.serviceImpl;

import com.training.java.entities.Student;
import com.training.java.repositories.StudentRepository;
import com.training.java.services.DisplayTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisplayTableImpl implements DisplayTableService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Map<Student, String> formatDates(List<Student> students) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Map<Student, String> formattedDates = new HashMap<>();
        for (Student student : students) {
            String formattedDateOfBirth = formatter.format(student.getDateOfBirth());
            formattedDates.put(student, formattedDateOfBirth);
        }
        return formattedDates;
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudent(int id, Student updatedStudent) {
    	System.out.println(updatedStudent);
        Student existingStudent = studentRepository.findById(id).orElse(null);
        
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setAdhaarCardNumber(updatedStudent.getAdhaarCardNumber());
            existingStudent.setCity(updatedStudent.getCity());
            existingStudent.setLanguagesKnown(updatedStudent.getLanguagesKnown());
            existingStudent.setStream(updatedStudent.getStream());
            existingStudent.setState(updatedStudent.getState());
            studentRepository.save(existingStudent);
        }
    }
	
}
