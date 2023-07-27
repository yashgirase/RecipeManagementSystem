package com.example.Controller;

import com.example.Entity.Comment;
import com.example.Service.AuthenticationTokenService;
import com.example.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("comment")
    public String addComment(@RequestBody @Valid Comment comment){
        return commentService.addComment(comment);
    }






}
