package com.training.java.repositories;

import com.training.java.entities.Student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findAllByName(String str);
	List<Student> findAllByNameIgnoreCase(String name);
	Page<Student> findByNameIgnoreCaseContaining(String string, Pageable pageable);
	List<Student> findByNameIgnoreCaseContaining(String string, Sort sort);
}
