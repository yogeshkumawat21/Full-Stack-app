package com.App.Yogesh.Controller;

import com.App.Yogesh.Models.User;
import com.App.Yogesh.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
     public List<User> getUsers()
     {
         List<User> users = userRepository.findAll();
         return users;
     }
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id ) throws Exception
    {
       Optional<User> user = userRepository.findById(id);
       if(user.isPresent())
       {
           return user.get();
       }
       throw new Exception("user not exist with userId"+id);
    }

     @PostMapping("/createUsers")
     public User createUser(@RequestBody User user)
     {
         User newUser = new User();
         newUser.setEmail(user.getEmail());
         newUser.setFirstName(user.getFirstName());
         newUser.setLastName(user.getLastName());
         newUser.setPassword(user.getPassword());
         newUser.setId(user.getId());

         User savedUser = userRepository.save(newUser);

         return savedUser;
     }

      @PutMapping("/updateUsers/{userId}")
     public User updateUser(@RequestBody User user , @PathVariable Integer userId ) throws Exception
     {
       Optional<User> user1 = userRepository.findById(userId);
       if(user1.isEmpty())
       {
           throw new Exception("user not exist with user Id"+userId);
       }

            User oldUser = user1.get();
       if(user.getFirstName()!=null)
       {
           oldUser.setFirstName(user.getFirstName());
       }
         if(user.getLastName()!=null)
         {
             oldUser.setLastName(user.getLastName());
         }
         if(user.getEmail()!=null)
         {
             oldUser.setEmail(user.getEmail());
         }
         User updatedUser = userRepository.save(oldUser
         );

         return  oldUser;
     }

@DeleteMapping("users/deleteUser/{userid}")
     public String deleteUser(@PathVariable int userId) throws Exception
     {
         Optional<User> user = userRepository.findById(userId);
         if(user.isEmpty())
         {
             throw new Exception("user not exist with user Id"+userId);
         }
         userRepository.delete(user.get());
         return
                 "user deleted Successfully with id"+userId;
     }
     }




