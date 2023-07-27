package com.example.Service;

import com.example.Entity.Comment;
import com.example.Repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;



    public String addComment(Comment comment) {
        comment.setCommentCreationTimestamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Commented successfully on recipe ";
    }


}
