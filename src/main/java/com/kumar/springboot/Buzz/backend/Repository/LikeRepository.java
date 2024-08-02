package com.kumar.springboot.Buzz.backend.Repository;

import com.kumar.springboot.Buzz.backend.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("SELECT l FROM likes l WHERE l.users.id=:userId")
    public List<Like> findByUserId(@Param("userId") Long userId);

    @Query("SELECT l FROM likes l WHERE l.users.id=:userId AND l.post.id=:postId")
    public Like isLikeExist(@Param("userId") Long userId,@Param("postId") Long postId);

    @Query("select l from likes l where l.post.id=:postId")
    public List<Like> findByPostId(@Param("postId") Long postId);

}
