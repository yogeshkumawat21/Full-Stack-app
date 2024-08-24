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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Retrieves all users.
     */
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a specific user by their ID.
     */
    @GetMapping("/api/users/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("User not found with ID " + id);
        }
        return user;
    }


    /**
     * Updates user information for a specific user ID.
     */
    @PutMapping("/api/updateUsers/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
        return userService.updateUser(user, userId);
    }

    /**
     * Allows one user to follow another user.
     */
    @PutMapping("/api/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        return userService.followUser(userId1, userId2);
    }

    /**
     * Searches for users based on a query string.
     */
    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }
}
