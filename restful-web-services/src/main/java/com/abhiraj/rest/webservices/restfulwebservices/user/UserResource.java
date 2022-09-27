package com.abhiraj.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
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
    public User retrieveUser (@PathVariable int id) {
        User user = userService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id :"+id);
        }
        return user;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser (@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
