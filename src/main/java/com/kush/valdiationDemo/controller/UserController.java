package com.kush.valdiationDemo.controller;

import com.kush.valdiationDemo.model.User;
import com.kush.valdiationDemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired // for clarity purposes, this annotation is not necessary
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String ,Object>> getAllUsers(){
        Map<String , Object> response  = new HashMap<>();
       List<User> users = userService.getAllUsers();
         response.put("returnObject", 200);
         response.put("ReturnObject", users);
         return new ResponseEntity<>(response , HttpStatus.OK);

    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String , Object>> getUserById(@PathVariable Long id){
        Map<String , Object> response  = new HashMap<>();
        User user = userService.getUserById(id);
        response.put("returnObject", 200);
        response.put("ReturnObject", user);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/users/add")
    public ResponseEntity<Map<String , Object>> addUser(@Valid @RequestBody User user){
        Map<String ,Object> response = new HashMap<>();
        User newUser = userService.addUser(user);
        response.put("returnObject", 201);
        response.put("ReturnObject", newUser);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }

}
