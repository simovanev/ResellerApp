package com.resellerapp.repository;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByUsernameAndPassword(String username , String password);

   Optional<User> findByEmail(String email);
}
