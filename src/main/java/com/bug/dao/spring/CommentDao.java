package com.bug.dao.spring;

import com.bug.model.Comment;
import com.bug.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findCommentsByIssueId(int issueId);
}
