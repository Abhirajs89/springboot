package com.abhiraj.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(path = "/users")
    public ResponseEntity<User> save(@RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
