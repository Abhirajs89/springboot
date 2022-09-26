package com.abhiraj.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/helloWorld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/helloBean")
    public HelloBean helloBean(){
        return new HelloBean("Hello World!");
    }

    @GetMapping(path = "/helloBean/pathVariable/{name}")
    public HelloBean helloBeanPathVariable(@PathVariable String name){
        return new HelloBean("Hello World "+name+"!");
    }
}
