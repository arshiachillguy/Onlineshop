package com.example.demo.controllerforUsers;

import com.example.demo.Users.user;
import com.example.demo.serviceforUsers.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("v1/user")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<List<user>> postusers(@Valid @RequestBody List<user> users){
        List<user> savedUsers = userService.saveuser(users);
        return ResponseEntity.ok(savedUsers);
    }

    @GetMapping
    public ResponseEntity<List<user>> showalluser(){
        List<user> users = userService.getallusers();
        return ResponseEntity.ok(users);
    }






}
