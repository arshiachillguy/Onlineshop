package com.example.demo.serviceforUsers;

import com.example.demo.Users.user;
import com.example.demo.repositoriesforusers.Usersrepository;
import org.apache.catalina.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public Usersrepository usersrepository;

    public void ResisterUser(@NotNull User user){
        if (usersrepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("user already existed !");
        }

    }

    public List<user> saveuser(List<user> user){
        return usersrepository.saveAll(user);
    }

    public List<user> getallusers() {
       return usersrepository.findAll();
    }




}
