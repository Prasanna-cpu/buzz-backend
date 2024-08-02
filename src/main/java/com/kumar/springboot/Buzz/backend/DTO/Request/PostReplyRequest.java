package com.kumar.springboot.Buzz.backend.DTO.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReplyRequest {

    private String content;

    private Long postId;

    private LocalDateTime createdAt;

    private String image;


}
