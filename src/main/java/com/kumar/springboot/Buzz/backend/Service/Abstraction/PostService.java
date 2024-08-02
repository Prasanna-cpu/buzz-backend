package com.kumar.springboot.Buzz.backend.Service.Abstraction;

import com.kumar.springboot.Buzz.backend.DTO.Request.PostReplyRequest;
import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.PostException;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;

import java.util.List;

public interface PostService {

    public Post createPost(Post req, Users user) throws UserException;

    public List<Post> findAllPosts();

    public Post repost(Long postId,Users user) throws UserException, PostException;

    public Post findById(Long postId) throws PostException;

    public void deleteById(Long postId,Long userId) throws PostException;


    public Post removeRepost(Long postId, Users user) throws PostException,UserException;

    public Post createdReply(Users user, PostReplyRequest request) throws PostException;

    public List<Post> getUserPost(Users user);

    public List<Post> findByLikesContainsUser(Users user);






}
