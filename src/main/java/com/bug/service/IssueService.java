package com.bug.service;

import com.bug.model.Issue;

public interface IssueService {

    Issue findById(int id);

    Iterable<Issue> findAll();

    Issue findBySummary(String name);

    void save(Issue issue);

    void update(Issue issue, int id);
}
