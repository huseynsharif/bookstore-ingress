package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationDAO extends JpaRepository<Verification, Integer> {

    Verification findVerificationByUser_Id(int id);

}