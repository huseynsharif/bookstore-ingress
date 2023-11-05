package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Integer> {
}
