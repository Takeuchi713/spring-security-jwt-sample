package com.takeuchi.springsecurityjwt.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takeuchi.springsecurityjwt.domain.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
