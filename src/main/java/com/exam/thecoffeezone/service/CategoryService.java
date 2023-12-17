package com.exam.thecoffeezone.service;

import com.exam.thecoffeezone.model.Category;
import com.exam.thecoffeezone.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

@Autowired
    CategoryRepo categoryRepo;
public List<Category> getAllCategory(){
   return categoryRepo.findAll();
    }
public void addCategory(Category category){
    categoryRepo.save(category);
}
    public void removeCategoryById(int id){
categoryRepo.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id){

    return categoryRepo.findById(id);

    }

}


