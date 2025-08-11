package com.example.demo.orderController;

import com.example.demo.ShopOrder.shoporder;
import com.example.demo.orderRepository.repositoryorder;
import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class controllerOrder {

    private final ProductService productService;
    private final repositoryorder repositoryorder;

    public controllerOrder(ProductService productService , repositoryorder repositoryorder){
        this.productService = productService;
        this.repositoryorder = repositoryorder;
    }

    // just show the shoporder form to user
    @GetMapping("v1/order")
    public String showOrderForm(@RequestParam Long productId, Model model) {
        // فقط محصول را به صفحه ارسال می‌کنیم
        Optional<Product> product = productService.getProductByID(productId) ;
        if (product.isPresent()){
            model.addAttribute("product" , product.get());
            model.addAttribute("order" , new shoporder());

            return "shoporder-form";
        }else{
            return "redirect:/v1/mainlist";
        }

    }
    @PostMapping("v1/order/submit")
    public String submitOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.getProductByID(productId); // استفاده از پارامتر ورودی

        if (product.isPresent()) {
            shoporder order = new shoporder();
            order.setProduct(product.get());
            order.setQuantity(quantity); // اضافه کردن مقدار quantity
            repositoryorder.save(order);

            redirectAttributes.addFlashAttribute("success", "سفارش با موفقیت ثبت شد!");
            return  "redirect:/v1/mainlist";

        } else {
            redirectAttributes.addFlashAttribute("error", "محصول یافت نشد!");
            return "redirect:shoporder-form"; // بازگشت به صفحه محصولات
        }

    }

}



