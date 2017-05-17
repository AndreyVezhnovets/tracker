package com.bug.dao.spring;

import com.bug.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectDao extends CrudRepository<Project, Integer> {

    Project findByName(String login);

    void deleteByName(String name);

}
