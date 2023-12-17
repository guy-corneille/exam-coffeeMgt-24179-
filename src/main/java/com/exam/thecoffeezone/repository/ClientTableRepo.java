package com.exam.thecoffeezone.repository;

import com.exam.thecoffeezone.model.ClientTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTableRepo extends JpaRepository<ClientTable,Integer> {
}
