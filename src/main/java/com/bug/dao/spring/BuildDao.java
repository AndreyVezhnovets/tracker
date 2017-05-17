package com.bug.dao.spring;

import com.bug.model.Build;
import org.springframework.data.repository.CrudRepository;

public interface BuildDao extends CrudRepository<Build, Integer> {

    Iterable<Build> findBuildsByProjectId(int projectId);
}
