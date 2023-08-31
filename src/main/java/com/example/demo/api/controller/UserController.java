package com.example.demo.api.controller;
import com.example.demo.api.model.User;
import com.example.demo.service.UserService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam Integer id){
        User user = userService.getUser(id).orElse(null);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/user")
    public ResponseEntity<User> postUser(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age, @RequestParam String email){
        User user = new User(id, name, age, email);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> putUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.getUser(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setAge(updatedUser.getAge());
            userToUpdate.setEmail(updatedUser.getEmail());

            userService.updateUser(userToUpdate);
            return ResponseEntity.ok("User updated successfully !!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !!");
        }
    }


    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id){
        boolean delete = userService.deleteUser(id);
        if (delete) {
            return ResponseEntity.ok("User deleted successfully !!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !!");
        }
    }

}