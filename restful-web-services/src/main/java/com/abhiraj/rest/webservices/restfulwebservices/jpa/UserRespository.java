package com.abhiraj.rest.webservices.restfulwebservices.jpa;

import com.abhiraj.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {
}
