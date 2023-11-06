package com.example.bookstorebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "subscribes")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Subscribe(Student student, Author author) {
        this.student = student;
        this.author = author;
    }
}
