package com.example.bookstorebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "age", nullable = false, unique = true)
    private int age;

    @ManyToMany
    @JoinTable(
            name = "books_students",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id",
                    referencedColumnName = "id"
            )
    )
    private List<Book> currentlyReadings;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
