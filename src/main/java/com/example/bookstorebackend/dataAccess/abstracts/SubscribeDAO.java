package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeDAO extends JpaRepository<Subscribe, Integer> {



}
