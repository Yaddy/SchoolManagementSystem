package com.yadirichukwu.schoolmanagementsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    private Student student;

    public StudentProfile() {};

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

}
