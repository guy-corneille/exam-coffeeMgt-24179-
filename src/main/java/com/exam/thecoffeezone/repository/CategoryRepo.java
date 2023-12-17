package com.exam.thecoffeezone.repository;

import com.exam.thecoffeezone.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
