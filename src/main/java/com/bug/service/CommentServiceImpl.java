package com.bug.service;

import com.bug.dao.spring.CommentDao;
import com.bug.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    public Comment findById(int id) {
        return commentDao.findOne(id);
    }

    public void insert(Comment comment) {
        commentDao.save(comment);
    }

    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    public void deleteById(int id) {
        commentDao.delete(id);
    }

    public Iterable<Comment> findAll() {
        return commentDao.findAll();
    }

    public Iterable<Comment> findCommentsByIssueId(int issueId) {
        return commentDao.findCommentsByIssueId(issueId);
    }


}
