package com.example.demo.controllerforproducts;

import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductViewController {
    @Autowired
    private ProductService productService;

//    show all products
    @GetMapping("v1/products/view")
    public String showproducts( Model model){
        model.addAttribute("products" , productService.getAllProducts());
        return "product-list";
    }
// show form for add new products
    @GetMapping("v1/products/add")
    public String showaddform(Model model){
        model.addAttribute("product" , new Product());
        return "product-form";
    }

// save that new products
    @PostMapping("v1/products/save")
    public String saveproducts(@ModelAttribute Product product , Model model ){
        productService.saveProducts(product);
        return "redirect:/v1/products/view";
    }

// delete that product
    @GetMapping("v1/products/delete/{id}")
    public String DeleteProducts(@PathVariable Long id){
        productService.deleteProductByID(id);
        return "redirect:/v1/products/view";
    }

// show the form of editing
    @GetMapping("v1/products/edit/{id}")
    public String showEditForm(@PathVariable Long id , Model model){
        Product product = productService.GETproductBYID(id);
        model.addAttribute("product" , product);
        model.addAttribute("editingProduct" , productService.GETproductBYID(id));
        return "product-list";
    }
// update product from old one to the new one
    @PostMapping("v1/products/edit/{id}")
    public String updateProduct(@PathVariable Long id , @ModelAttribute Product updatedProdcut , RedirectAttributes redirectAttributes){
        productService.updateProduct(id , updatedProdcut);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        return "redirect:/v1/products/view";
    }


    //show product to main user for buy some things
    @GetMapping("v1/MainList")
    public String showMainPage(Model model){
        model.addAttribute("products" , productService.getAllProducts());
        return "product-list-main";
    }

}
