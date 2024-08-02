package com.kumar.springboot.Buzz.backend.Repository;

import com.kumar.springboot.Buzz.backend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {


     Optional<Users> findByEmail(String email);


     @Query("SELECT DISTINCT u FROM Users u WHERE u.fullName LIKE %:query% OR u.email LIKE %:query%")
     List<Users> searchUser(@Param("query") String query);


}
