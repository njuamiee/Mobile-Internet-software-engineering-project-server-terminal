package org.example.server.repository;

import org.example.server.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer commentId);
    List<Comment> findByNewId(Integer newId);
}
