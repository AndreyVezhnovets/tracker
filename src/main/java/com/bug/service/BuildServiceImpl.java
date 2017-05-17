package com.bug.service;

import com.bug.dao.spring.BuildDao;
import com.bug.model.Build;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BuildServiceImpl implements BuildService {

    @Autowired
    BuildDao buildDao;

    public void save(Build build) {
        buildDao.save(build);
    }
}
