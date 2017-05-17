package com.bug.dao.spring;

import com.bug.model.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueDao extends CrudRepository<Issue, Integer> {

   Issue findBySummary(String name);
}
