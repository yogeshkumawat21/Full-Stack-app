package com.App.Yogesh.Repository;

import com.App.Yogesh.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}

