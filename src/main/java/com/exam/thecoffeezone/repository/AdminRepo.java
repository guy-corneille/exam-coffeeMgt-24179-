package com.exam.thecoffeezone.repository;

import com.exam.thecoffeezone.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
