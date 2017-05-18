package com.bug.service;

import com.bug.model.Build;
import com.bug.model.Project;

public interface ProjectService {

    Project findById(int id);

    Project findByName(String login);

    void save(Project employee);

    void update(Project project, int id, String buildId);

    void delete(Project employee);

    void deleteByName(String login);

    Iterable<Project> findAll();

    Iterable<Build> getVersions(int projectId);

}
