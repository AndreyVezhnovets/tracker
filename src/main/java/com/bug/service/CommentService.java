package com.bug.service;

import com.bug.model.Comment;

public interface CommentService {

    Comment findById(int id);

    void insert(Comment comment);

    void delete(Comment comment);

    void deleteById(int id);

    Iterable<Comment> findAll();

    Iterable<Comment> findCommentsByIssueId(int issueId);

}
