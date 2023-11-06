package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Student;
import com.example.bookstorebackend.entities.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeDAO extends JpaRepository<Subscribe, Integer> {

    List<Subscribe> findAllByAuthor_Id(int authorId);

    @Query("SELECT s.student FROM Subscribe s WHERE s.author.id = :authorId")
    List<Student> findStudentsByAuthorId(int authorId);

}
