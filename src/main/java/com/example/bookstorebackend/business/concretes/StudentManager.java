package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.StudentService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.AlreadyReadException;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.NotFoundException;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.core.utilities.results.SuccessDataResult;
import com.example.bookstorebackend.core.utilities.results.SuccessResult;
import com.example.bookstorebackend.dataAccess.abstracts.*;
import com.example.bookstorebackend.entities.*;
import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {

    private final StudentDAO studentDAO;
    private final UserDAO userDAO;
    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final SubscribeDAO subscribeDAO;

    @Override
    public Result add(StudentRequestDTO studentRequestDTO) {

        User user = this.userDAO.findById(studentRequestDTO.getUserId())
                .orElseThrow(()->new NotFoundException("Cannot find user with given id: " + studentRequestDTO.getUserId()));

        Student newStudent = new Student(
                studentRequestDTO.getName(),
                studentRequestDTO.getAge(),
                user
        );
        this.studentDAO.save(newStudent);
        return new SuccessResult("Student was saved successfully");
    }

    @Override
    public Result update(StudentRequestDTO studentRequestDTO) {

        Student student = this.studentDAO.findStudentByUser_Id(studentRequestDTO.getUserId());
        if (student == null) {
            throw new NotFoundException("Cannot find student with userId: " + studentRequestDTO.getUserId());
        }

        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        this.studentDAO.save(student);
        return new SuccessResult("Student updated successfully.");
    }

    @Override
    public Result readBook(int bookId, int studentId) {

        Book book = this.bookDAO.findById(bookId).orElseThrow(
                ()-> new NotFoundException("Cannot find book with given bookId: " + bookId)
        );

        Student student = this.studentDAO.findById(studentId).orElseThrow(
                ()-> new NotFoundException("Cannot find student with given studentId: " + studentId)
        );

        if (student.getCurrentlyReadings().contains(book)){
            throw new AlreadyReadException("You are already reading this book.");
        }

        student.getCurrentlyReadings().add(book);
        this.studentDAO.save(student);

        return new SuccessResult("Student is reading: " + book.getName());
    }

    @Override
    public Result finishBook(int bookId, int studentId) {

        Book book = this.bookDAO.findById(bookId).orElseThrow(
                ()-> new NotFoundException("Cannot find book with given bookId: " + bookId)
        );

        Student student = this.studentDAO.findById(studentId).orElseThrow(
                ()-> new NotFoundException("Cannot find student with given studentId: " + studentId)
        );

        if (!student.getCurrentlyReadings().contains(book)){
            throw new NotFoundException("You are not reading this book.");
        }

        student.getCurrentlyReadings().remove(book);
        this.studentDAO.save(student);

        return new SuccessResult("Successfully finished.");
    }

    @Override
    public DataResult<List<BookResponseDTO>> getAllCurrentlyReadingsByStudentId(int studentId) {

        Student student = this.studentDAO.findById(studentId).orElseThrow(
                ()-> new NotFoundException("Cannot find student with given studentId: " + studentId)
        );

        List<Book> books = student.getCurrentlyReadings();

        if (books.isEmpty()){
            throw new NotFoundException("Cannot find book with given studentId: " + studentId);
        }


        List<BookResponseDTO> response = books.stream().map(
                (book -> new BookResponseDTO(
                        book.getId(),
                        book.getName(),
                        book.getAuthor().getName()
                ))
        ).toList();

        return new SuccessDataResult<>(response, "All books student currently reading: " + studentId);
    }

    @Override
    public Result subscribe(int studentId, int authorId) {

        Student student = this.studentDAO.findById(studentId).orElseThrow(
                ()-> new NotFoundException("Cannot find student with given studentId: " + studentId)
        );

        Author author = this.authorDAO.findById(authorId).orElseThrow(
                ()-> new NotFoundException("Cannot find author with given authorId: " + authorId)
        );

        Subscribe subscribe = new Subscribe(student, author);
        this.subscribeDAO.save(subscribe);
        return new SuccessResult("Successfully subscribed.");
    }
}
