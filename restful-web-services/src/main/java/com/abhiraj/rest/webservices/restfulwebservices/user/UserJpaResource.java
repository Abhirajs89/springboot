package com.abhiraj.rest.webservices.restfulwebservices.user;

import com.abhiraj.rest.webservices.restfulwebservices.jpa.UserRespository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRespository repository;
    public UserJpaResource(UserRespository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser (@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if(user == null){
            throw new UserNotFoundException("id :"+id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(link.withRel("all-users"));
        return userEntityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser (@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
