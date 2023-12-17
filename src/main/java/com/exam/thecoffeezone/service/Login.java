package com.exam.thecoffeezone.service;

public interface Login {
    boolean authenticateUser(String email, String password);
    boolean registerAdmin(String fullNames, String email, String password);
}
