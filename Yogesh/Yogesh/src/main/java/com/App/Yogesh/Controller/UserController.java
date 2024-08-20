package com.App.Yogesh.Controller;

import com.App.Yogesh.Models.User;
import com.App.Yogesh.Repository.UserRepository;
import com.App.Yogesh.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
     public List<User> getUsers()
     {
         List<User> users = userRepository.findAll();
         return users;
     }
    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer id ) throws Exception {
           return userRepository.findById(id);
    }

     @PostMapping("/createUsers")
     public User createUser(@RequestBody User user)
     {

         User savedUser = userService.registeruser(user);
         return savedUser;
     }

      @PutMapping("/updateUsers/{userId}")
     public User updateUser(@RequestBody User user , @PathVariable Integer userId ) throws Exception
     {
        User savedUser=userService.updateUser(user,userId);
        return savedUser;
     }

     @PutMapping("/follow/{userId1}/{userId2}")
     public User followUserHandler(@PathVariable Integer userId1 , @PathVariable Integer userId2) throws Exception {
         User user =userService.followUser(userId1,userId2);

         return user;
     }

     @GetMapping("/users/search")
     public List<User> searchUser(@RequestParam("query") String query)
     {
         List<User> users= userService.searchUser((query));
         return users;
     }
     }




