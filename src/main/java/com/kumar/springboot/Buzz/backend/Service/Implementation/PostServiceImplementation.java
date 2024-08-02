package com.kumar.springboot.Buzz.backend.Service.Implementation;

import com.kumar.springboot.Buzz.backend.DTO.Request.PostReplyRequest;
import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;
import com.kumar.springboot.Buzz.backend.Exceptions.PostException;
import com.kumar.springboot.Buzz.backend.Exceptions.UserException;
import com.kumar.springboot.Buzz.backend.Repository.PostRepository;
import com.kumar.springboot.Buzz.backend.Service.Abstraction.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {


    private final PostRepository postRepository;

    /**
     * @param req
     * @param user
     * @return
     * @throws UserException
     */
    @Override
    public Post createPost(Post req, Users user) throws UserException {
        Post post=new Post();
        post.setContent(req.getContent());
        post.setCreatedAt(req.getCreatedAt());
        post.setImage(req.getImage());
        post.setUsers(user);
        post.setPost(true);
        post.setVideo(req.getVideo());
        return postRepository.save(post);

    }

    /**
     * @return
     */
    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByIsPostTrueOrderByCreatedAtDesc();
    }

    /**
     * @param postId
     * @param user
     * @return
     * @throws UserException
     * @throws PostException
     */
    @Override
    public Post repost(Long postId, Users user) throws UserException, PostException {
        Post post=findById(postId);

        if(post.getRepostUser().contains(user)){
            post.getRepostUser().remove(user);
        }

        else{
            post.getRepostUser().add(user);
        }

        return postRepository.save(post);
    }

    /**
     * @param postId
     * @return
     * @throws PostException
     */
    @Override
    public Post findById(Long postId) throws PostException {
        Post post=postRepository.findById(postId).orElseThrow(()->new PostException("Post not found with id:"+postId));

        return post;

    }

    /**
     * @param postId
     * @throws PostException
     */
    @Override
    public void deleteById(Long postId, Long userId) throws PostException {
        Post post=findById(postId);

        if(!userId.equals(post.getUsers().getId())){
            throw new PostException("Cannot delete another user's  post");
        }
        else{
            postRepository.deleteById(postId);
        }
    }



    /**
     * @param postId
     * @param user
     * @return
     * @throws PostException
     * @throws UserException
     */
    @Override
    public Post removeRepost(Long postId, Users user) throws PostException, UserException {

        return null;
    }

    /**
     * @param user
     * @param req
     * @return
     * @throws PostException
     */
    @Override
    public Post createdReply(Users user, PostReplyRequest req) throws PostException {

        Post replyFor=findById(req.getPostId());

        if(replyFor==null){
            throw new PostException("Post not found with id:"+req.getPostId());
        }
        Post post=new Post();
        post.setContent(req.getContent());
        post.setCreatedAt(req.getCreatedAt());
        post.setImage(req.getImage());
        post.setUsers(user);
        post.setPost(true);
        post.setPost(false);
        post.setReplyFor(replyFor);

        Post savedReply=postRepository.save(post);

        post.getReplies().add(savedReply);

        postRepository.save(replyFor);
        return replyFor;

    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<Post> getUserPost(Users user) {
        return postRepository.findByRepostUserContainsOrUser_IdAndIsPostTrueOrderByCreatedAtDesc(user,user.getId());
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<Post> findByLikesContainsUser(Users user) {
        return postRepository.findByLikesUser_id(user.getId());
    }
}
