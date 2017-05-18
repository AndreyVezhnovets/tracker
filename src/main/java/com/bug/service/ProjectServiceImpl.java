package com.bug.service;

import com.bug.dao.spring.BuildDao;
import com.bug.dao.spring.ProjectDao;
import com.bug.model.Build;
import com.bug.model.Project;
import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    public ProjectDao projectDao;
    @Autowired
    BuildDao buildDao;

    public Project findById(int id) {
        return projectDao.findOne(id);
    }

    public Iterable<Build> getVersions(int projectId) {
        return buildDao.findBuildsByProjectId(projectId);
    }

    public Project findByName(String login) {
        return projectDao.findByName(login);
    }

    public Iterable<Project> findAll() {
        return projectDao.findAll();
    }

    public void delete(Project employee) {
        projectDao.delete(employee);
    }

    public void deleteByName(String name) {
        projectDao.deleteByName(name);
    }

    public void save(Project project) {
        projectDao.save(project);
    }

    public void update(Project project, int id, String buildId) {

        Project old = findById(id);
        old.setName(project.getName());
        old.setDescription(project.getDescription());
        old.setManager(project.getManager());
        save(old);
        if (Streams.stream(getVersions(id)).noneMatch(a -> a.getVersion().equals(buildId))) {
            buildDao.save(new Build(buildId, id));
        }
    }
}
