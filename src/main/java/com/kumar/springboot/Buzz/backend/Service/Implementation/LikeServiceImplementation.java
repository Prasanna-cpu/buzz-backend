package com.kumar.springboot.Buzz.backend.Service.Implementation;

import com.kumar.springboot.Buzz.backend.Entity.Like;
import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.PostException;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;
import com.kumar.springboot.Buzz.backend.Repository.LikeRepository;
import com.kumar.springboot.Buzz.backend.Repository.PostRepository;
import com.kumar.springboot.Buzz.backend.Service.Abstraction.LikeService;
import com.kumar.springboot.Buzz.backend.Service.Abstraction.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class LikeServiceImplementation implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final PostRepository postRepository;


    /**
     * @param postId
     * @param user
     * @return
     * @throws UserException
     * @throws PostException
     */
    @Override
    public Like likePost(Long postId, Users user) throws UserException, PostException {
       Like existingLike=likeRepository.isLikeExist(user.getId(),postId);
       if(existingLike!=null){
           likeRepository.deleteById(existingLike.getId());
           return existingLike;
       }
       Post post=postService.findById(postId);

       Like like=new Like();
       like.setPost(post);
       like.setUsers(user);

       Like savedLike=likeRepository.save(like);

       post.getLikes().add(savedLike);

       postRepository.save(post);

       return savedLike;

    }

    /**
     * @param postId
     * @return
     * @throws PostException
     */
    @Override
    public List<Like> getAllLikes(Long postId) throws PostException {
        Post post=postService.findById(postId);
        List<Like> likes=likeRepository.findByPostId(postId);
        return likes;
    }
}
