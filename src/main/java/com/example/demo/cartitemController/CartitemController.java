package com.example.demo.cartitemController;

import com.example.demo.CartITEMservice.cartitemservice;
import com.example.demo.cartitem.CartITEM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("v1/orderbasket")
public class CartitemController {

    @Autowired
    private cartitemservice cartitemservice;


    // نمایش صفحه مدیریت
    @GetMapping("/management")
    public String showManagementPage() {
        return "orderBasket"; // نمایش فایل HTML
    }

    // افزودن آیتم (با redirect به صفحه مدیریت)
    @PostMapping("/add")
    public String addItemToCart(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam Long quantity,
            RedirectAttributes redirectAttributes) {

        try {
            cartitemservice.additemtoCart(cartId, productId, quantity);
            redirectAttributes.addFlashAttribute("success", "محصول با موفقیت اضافه شد");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect: /cart/items/management?cartId=" + cartId;
    }

    // ویرایش آیتم
    @PostMapping("/{itemId}/edit")
    public String updateItem(
            @PathVariable Long itemId,
            @RequestParam int newQuantity,
            @RequestParam Long cartId, // برای redirect
            RedirectAttributes redirectAttributes) {

        try {
            cartitemservice.EditITEM(itemId, newQuantity);
            redirectAttributes.addFlashAttribute("success", "تعداد با موفقیت ویرایش شد");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/cart/items/management?cartId=" + cartId;
    }

    // حذف آیتم
    @PostMapping("/{itemId}/delete")
    public String deleteItem(
            @PathVariable Long itemId,
            @RequestParam Long cartId,
            RedirectAttributes redirectAttributes) {

        try {
            cartitemservice.DeleteITEM(itemId);
            redirectAttributes.addFlashAttribute("success", "آیتم با موفقیت حذف شد");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/cart/items/management?cartId=" + cartId;
    }

    // مشاهده آیتم‌های سبد
    @GetMapping("/management2")
    public String viewCartItems(
            @RequestParam Long cartId,
            Model model) {

        try {
            List<CartITEM> items = cartitemservice.getItemsByCartId(cartId);
            model.addAttribute("cartItems", items);
            model.addAttribute("cartId", cartId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "orderBasket";
    }
}
