package com.training.java.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.java.entities.Student;
import com.training.java.repositories.StudentRepository;
import com.training.java.services.RegistrationService;

@Service
public class RegistrationImpl implements RegistrationService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }
}
