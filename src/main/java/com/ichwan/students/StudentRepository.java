package com.ichwan.students;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public List<Student> cumlaudeStudents(Double gpa) {
        return list("SELECT s FROM Student s WHERE s.gpa > ?1", gpa);
    }
}
