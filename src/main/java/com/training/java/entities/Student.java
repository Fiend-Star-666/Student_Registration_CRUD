package com.training.java.entities;

import com.training.java.entities.enums.StatesOfIndia;
import com.training.java.entities.enums.StreamOfStudents;
import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

    @Column(name = "adhaarCardNumber", nullable = false, unique = true)
    private Long adhaarCardNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @ElementCollection
    @CollectionTable(name = "student_languages", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "language")
    private Set<String> languagesKnown;

    @Column(name = "stream", nullable = false)
    private StreamOfStudents stream;

    @Column(name = "state", nullable = false)
    private StatesOfIndia state;
    
}
