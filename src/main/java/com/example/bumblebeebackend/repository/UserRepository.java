package com.example.bumblebeebackend.repository;

import com.example.bumblebeebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByemail(String email);
}
