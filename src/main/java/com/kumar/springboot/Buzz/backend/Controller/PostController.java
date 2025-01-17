package com.kumar.springboot.Buzz.backend.Controller;


import com.kumar.springboot.Buzz.backend.DTO.PostDTO;

import com.kumar.springboot.Buzz.backend.Service.Abstraction.PostService;
import com.kumar.springboot.Buzz.backend.Service.Abstraction.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class PostController {


    private final PostService postService;

    private final UserService userService;





}
