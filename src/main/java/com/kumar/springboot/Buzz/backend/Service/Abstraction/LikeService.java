package com.kumar.springboot.Buzz.backend.Service.Abstraction;

import com.kumar.springboot.Buzz.backend.Entity.Like;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.PostException;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;

import java.util.List;

public interface LikeService {

    public Like likePost(Long postId, Users user) throws UserException, PostException;

    public List<Like> getAllLikes(Long postId) throws PostException;



}
