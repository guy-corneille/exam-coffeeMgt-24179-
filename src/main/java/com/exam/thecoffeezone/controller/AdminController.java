package com.exam.thecoffeezone.controller;

import com.exam.thecoffeezone.dto.ProductDTO;
import com.exam.thecoffeezone.model.Category;
import com.exam.thecoffeezone.model.ClientTable;
import com.exam.thecoffeezone.model.Product;
import com.exam.thecoffeezone.service.CategoryService;
import com.exam.thecoffeezone.service.ClientService;
import com.exam.thecoffeezone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final ClientService clientService;
    @Autowired
    public AdminController(ClientService clientService) {
        this.clientService = clientService;
    }

    public  static  String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
@Autowired
    ProductService productService;
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories",
                categoryService.getAllCategory());
        return "categories";

    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category",
                new Category());
        return "categoriesAdd";

    }
    @PostMapping("/admin/categories/add")
    public String postCatAtt
            (@ModelAttribute("categories")Category category)
    {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
public String deleteCat(@PathVariable int id){
categoryService.removeCategoryById(id);
return "redirect:/admin/categories";

    }
    @GetMapping("/admin/categories/update/{id}")
    public String  updateCat(@PathVariable int id ,Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";

        }else {
            return "404";
        }


    }
// Product section
@GetMapping("/admin/products")
public String product(Model model){
        model.addAttribute("products",
                productService.getAllProduct())
    ;
    return "products";

}
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model) {
        model.addAttribute("productsDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productsDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProd(@PathVariable Long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";

    }
    @GetMapping("/admin/products/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productsDTO", productDTO);

        return "productsAdd";
    }
//view tables
@GetMapping("/admin/viewTables")
public String viewTables(Model model) {
    List<ClientTable> clientTables = clientService.findAllClientTables();
    model.addAttribute("clientTables", clientTables);
    return "viewTables";
}

    }


