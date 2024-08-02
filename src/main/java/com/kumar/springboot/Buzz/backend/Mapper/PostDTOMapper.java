package com.kumar.springboot.Buzz.backend.Mapper;

import com.kumar.springboot.Buzz.Utils.PostUtil;
import com.kumar.springboot.Buzz.backend.DTO.PostDTO;
import com.kumar.springboot.Buzz.backend.DTO.UserDTO;
import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;

import java.util.ArrayList;
import java.util.List;

public class PostDTOMapper {

    public static PostDTO toPostDTO(Post post, Users req){
        UserDTO user=UserDTOMapper.mapToUserDTO(post.getUsers());

        boolean isLiked= PostUtil.isLikedByReqUser(post.getUsers(),post);

        boolean isReposted=PostUtil.isRepostedByReqUser(req,post);


        List<Long> repostUserId=new ArrayList<>();

        for(Users user1:post.getRepostUser()){
            repostUserId.add(user1.getId());
        }


        PostDTO postDTO=new PostDTO();

        postDTO.setId(post.getId());

        postDTO.setContent(post.getContent());

        postDTO.setCreatedAt(post.getCreatedAt());

        postDTO.setImage(post.getImage());

        postDTO.setTotalLikes(post.getLikes().size());

        postDTO.setTotalReplies(post.getReplies().size());

        postDTO.setTotalReposts(post.getRepostUser().size());

        postDTO.setUser(user);

        postDTO.setLiked(isLiked);

        postDTO.setRepostUsersId(repostUserId);

        postDTO.setReposted(isReposted);

        postDTO.setReplyPosts(toPostDTOList(post.getReplies(),req));

        postDTO.setVideo(post.getVideo());

        return postDTO;

    }

    public static List<PostDTO> toPostDTOList(List<Post> posts,Users req){
        List<PostDTO> postDTOList=new ArrayList<>();
        for(Post post:posts){
            postDTOList.add(toPostDTO(post,req));
        }
        return postDTOList;
    }

    public static PostDTO toReplyPostDTO(Post post,Users req){
        UserDTO user=UserDTOMapper.mapToUserDTO(post.getUsers());

        boolean isLiked= PostUtil.isLikedByReqUser(post.getUsers(),post);

        boolean isReposted=PostUtil.isRepostedByReqUser(req,post);


        List<Long> repostUserId=new ArrayList<>();

        for(Users user1:post.getRepostUser()){
            repostUserId.add(user1.getId());
        }


        PostDTO postDTO=new PostDTO();

        postDTO.setId(post.getId());

        postDTO.setContent(post.getContent());

        postDTO.setCreatedAt(post.getCreatedAt());

        postDTO.setImage(post.getImage());

        postDTO.setTotalLikes(post.getLikes().size());

        postDTO.setTotalReplies(post.getReplies().size());

        postDTO.setTotalReposts(post.getRepostUser().size());

        postDTO.setUser(user);

        postDTO.setLiked(isLiked);

        postDTO.setReposted(isReposted);

        postDTO.setRepostUsersId(repostUserId);

        postDTO.setVideo(post.getVideo());

        return postDTO;
    }


}
