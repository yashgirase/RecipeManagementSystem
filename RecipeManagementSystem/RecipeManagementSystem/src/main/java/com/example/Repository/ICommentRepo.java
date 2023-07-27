package com.example.Repository;

import com.example.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // not needed as those classes which implemented jpa repository already has @Repository on them so here it is optional
public interface ICommentRepo extends JpaRepository<Comment, Integer> {

}
