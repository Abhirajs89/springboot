package com.abhiraj.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public EntityModel<User> retrieveUser (@PathVariable int id) {
        User user = userService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id :"+id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(link.withRel("all-users"));
        return userEntityModel;
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
