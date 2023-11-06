package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Book;
import com.example.bookstorebackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Integer> {

    Student findStudentByUser_Id(int userId);

    List<Student> getAllByCurrentlyReadingsContains(Book book);

}
