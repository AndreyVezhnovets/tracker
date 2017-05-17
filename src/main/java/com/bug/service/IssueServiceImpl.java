package com.bug.service;

import com.bug.dao.spring.IssueDao;
import com.bug.model.Issue;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("IssueService")
public class IssueServiceImpl implements IssueService {

    @Autowired
    public IssueDao issueDao;
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Issue findById(int id) {
        return issueDao.findOne(id);
    }

    public Iterable<Issue> findAll() {
        return issueDao.findAll();
    }

    public Issue findBySummary(String name) {
        return issueDao.findBySummary(name);
    }

    public void save(Issue issue) {
        issueDao.save(issue);
    }

    public void update(Issue entity, int id) {
        Issue issue = issueDao.findOne(id);
        issue.setSummary(entity.getSummary());
        issue.setDescription(entity.getDescription());
        issue.setStatus(entity.getStatus());
        issue.setType(entity.getType());
        issue.setPriority(entity.getPriority());
        issue.setProject(entity.getProject());
        issue.setBuildVersion(entity.getBuildFound());
        issue.setResolution(entity.getResolution());
        issueDao.save(issue);
    }
}
