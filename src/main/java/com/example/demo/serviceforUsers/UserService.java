package com.example.demo.serviceforUsers;

import com.example.demo.Users.user;
import com.example.demo.repositoriesforusers.Usersrepository;
import jakarta.transaction.Transactional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {


    @Autowired
    public Usersrepository usersrepository;

    public List<user> saveuser(List<user> user){
        return usersrepository.saveAll(user);
    }

    public List<user> getallusers() {
       return usersrepository.findAll();
    }

    @Transactional
    public user registerUser(@NotNull user user){
        if (usersrepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("username already exists !");
        }
        if (usersrepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("email already exits ! ");
        }
        return usersrepository.save(user);
    }


}
