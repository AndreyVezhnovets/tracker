package com.bug.controller;

import com.bug.exeption.ProjectException;
import com.bug.model.Build;
import com.bug.model.Project;
import com.bug.service.BuildService;
import com.bug.service.EmployeeService;
import com.bug.service.ProjectService;
import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.Collections;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    BuildService buildService;

    @RequestMapping(value = {"/projects"})
    public String getProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects";
    }

    @RequestMapping(value = {"/addProject"})
    public String addProject(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "AddProject";
    }
    @Transactional
    @RequestMapping(value = {"/editProject"})
    public String editProject(Model model, @RequestParam("projectId") Integer projectId) {
        model.addAttribute("project", projectService.findById(projectId));
        model.addAttribute("builds", projectService.getVersions(projectId));
        model.addAttribute("employees", employeeService.findAll());
        return "editProject";
    }

    @RequestMapping(value = "project/add/new")
    @ResponseBody
    public String addNewProject(@RequestParam("name") String name, @RequestParam("description") String description,
                              @RequestParam("build") String build, @RequestParam("manager") String manager) throws ProjectException {


        Project project = new Project(name, description, manager);
        project.setBuildVersions( Collections.singletonList(new Build(build)));
        projectService.save(project);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/project/edit")
    public String editProject(@RequestParam("name") String name, @RequestParam("description") String description,
                              @RequestParam("build") String build, @RequestParam("manager") String manager, @RequestParam("id") Integer id) throws ProjectException {

        Project old = projectService.findById(id);
        old.setName(name);
        old.setDescription(description);
        old.setManager(manager);
        projectService.save(old);
        if (Streams.stream(projectService.getVersions(id)).noneMatch(a -> a.getVersion().equals(build))) {
            buildService.save(new Build(build, id));
//            old.getBuildVersions().add();
//            old.setBuildVersions( );
        }

        return "success";
    }
}
