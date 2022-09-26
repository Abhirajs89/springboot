package com.abhiraj.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {
    public static List<User> users = new ArrayList<>();
    static{
        users.add(new User(1,"Abhiraj", LocalDate.now().minusYears(33)));
        users.add(new User(2,"Anuja", LocalDate.now().minusYears(30)));
        users.add(new User(3,"Suyog", LocalDate.now().minusYears(37)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }
}
