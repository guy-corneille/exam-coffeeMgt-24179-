package com.exam.thecoffeezone.controller;

import com.exam.thecoffeezone.global.GlobalData;
import com.exam.thecoffeezone.model.Product;
import com.exam.thecoffeezone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id ){
        GlobalData.cart.add(productService.getProductById(id).get());

        return "redirect:/menu";

    }
    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";

    }
    @DeleteMapping("/removeFromCart/{id}")
    public String removeFromCart(@PathVariable int id) {
        // Implement logic to remove the product with the given id from the cart
        GlobalData.cart.removeIf(product -> product.getId() == id);
        return "redirect:/cart";
    }


}
