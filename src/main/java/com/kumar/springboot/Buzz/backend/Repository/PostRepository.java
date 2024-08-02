package com.kumar.springboot.Buzz.backend.Repository;

import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM post p WHERE p.isPost = true ORDER BY p.createdAt DESC")
    List<Post> findAllByIsPostTrueOrderByCreatedAtDesc();

    @Query("SELECT p FROM post p WHERE (:user MEMBER OF p.repostUser OR p.users.id = :userId) AND p.isPost = true ORDER BY p.createdAt DESC")
    List<Post> findByRepostUserContainsOrUser_IdAndIsPostTrueOrderByCreatedAtDesc(@Param("user") Users user, @Param("userId") Long userId);

    @Query("SELECT p FROM post p JOIN p.likes l WHERE l.users = :user ORDER BY p.createdAt DESC")
    List<Post> findByLikesContainingOrderByCreatedAtDesc(@Param("user") Users user);

    @Query("SELECT p FROM post p JOIN p.likes l WHERE l.users.id = :userId ORDER BY p.createdAt DESC")
    List<Post> findByLikesUser_id(@Param("userId") Long userId);
}
