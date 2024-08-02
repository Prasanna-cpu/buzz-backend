package com.kumar.springboot.Buzz.Utils;

import com.kumar.springboot.Buzz.backend.Entity.Like;
import com.kumar.springboot.Buzz.backend.Entity.Post;
import com.kumar.springboot.Buzz.backend.Entity.Users;

public class PostUtil {

    public static boolean isRepostedByReqUser(Users reqUser,Post post){
        for(Users user:post.getRepostUser()){
            if(post.getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }

    public static boolean isLikedByReqUser(Users reqUser, Post post){
        for (Like like: post.getLikes()){
            if(like.getUsers().getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }

}
