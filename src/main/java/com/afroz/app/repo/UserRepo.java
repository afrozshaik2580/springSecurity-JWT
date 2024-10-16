package com.afroz.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afroz.app.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	User findByUsername(String username);
}

