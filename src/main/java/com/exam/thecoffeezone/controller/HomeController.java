package com.exam.thecoffeezone.controller;

import com.exam.thecoffeezone.global.GlobalData;
import com.exam.thecoffeezone.service.CategoryService;
import com.exam.thecoffeezone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
@Autowired
    CategoryService categoryService;
@Autowired
    ProductService productService;

    @GetMapping("/index")
    public String showClientForm(Model model) {

        return "index";
    }
    @GetMapping("/about")
    public String showAbout(Model model) {

        return "About";
    }
    @GetMapping("/menu")
    public  String showMenu(Model model){
         model.addAttribute("categories", categoryService.getAllCategory());
         model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("cartCount",GlobalData.cart.size());
         return "menu";
    }
    @GetMapping("/menu/category/{id}")
    public  String showMenuByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService. getAllProductByCategoryId(id));
        return "menu";
    }

    @GetMapping("/menu/viewProduct/{id}")
    public  String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount",GlobalData.cart.size());

        return "viewProduct";
    }}

/*        @GetMapping("/admin")
        public String redirectToLogin() {
            return "redirect:/login";
        }
    }*/


