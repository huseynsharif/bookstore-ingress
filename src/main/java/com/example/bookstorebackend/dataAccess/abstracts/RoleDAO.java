package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.ERole;
import com.example.bookstorebackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(ERole rolename);

}
