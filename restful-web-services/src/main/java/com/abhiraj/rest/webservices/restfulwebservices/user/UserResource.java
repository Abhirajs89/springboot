package com.abhiraj.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {
    UserDao userService;

    public UserResource(UserDao userDao) {
        this.userService = userDao;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser (@PathVariable int id){
        return userService.findOne(id);
    }
}
