package com.example.demo.controllerforUsers;

import com.example.demo.Users.User;
import com.example.demo.serviceforUsers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("v1/user")
public class    UserController {
    @Autowired
    public UserService userService;


    @PostMapping
    public ResponseEntity<List<User>> postusers(@Valid @RequestBody List<User> Users){
        List<User> savedUsers = userService.saveuser(Users);
        return ResponseEntity.ok(savedUsers);
    }

    @GetMapping
    public ResponseEntity<List<User>> showalluser(){
        List<User> Users = userService.getallusers();
        return ResponseEntity.ok(Users);
    }



    @GetMapping("register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user" , new User());
        return "register";
    }
    @PostMapping("register")
    public String processRegistration(@Valid @ModelAttribute("User") User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        redirectAttributes.addAttribute("success", true);
        return "redirect:/v1/products/view";
    }
}
