package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.StudentService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.NotFoundException;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.core.utilities.results.SuccessResult;
import com.example.bookstorebackend.dataAccess.abstracts.StudentDAO;
import com.example.bookstorebackend.dataAccess.abstracts.UserDAO;
import com.example.bookstorebackend.entities.Student;
import com.example.bookstorebackend.entities.User;
import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {

    private final StudentDAO studentDAO;
    private final UserDAO userDAO;

    @Override
    public Result add(StudentRequestDTO studentRequestDTO) {

        User user = this.userDAO.findById(studentRequestDTO.getUserId())
                .orElseThrow(()->new NotFoundException("Cannot find user with given id: "));

        Student newStudent = new Student(
                studentRequestDTO.getName(),
                studentRequestDTO.getAge(),
                user
        );
        this.studentDAO.save(newStudent);
        return new SuccessResult("Student was saved successfully");
    }
}
