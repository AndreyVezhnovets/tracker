package com.bug.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private List<Build> buildVersions;

    @Column(name = "manager", nullable = false)
    private String manager;

    public Project() {
    }

/*    public Project(String name, String description, List<Build> buildVersions, String manager) {
        this.name = name;
        this.description = description;
        this.buildVersions = buildVersions;
        this.manager = manager;
    }*/

    public Project(String name, String description, String manager) {
        this.name = name;
        this.description = description;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Build> getBuildVersions() {
        return buildVersions;
    }

    public void setBuildVersions(List<Build> buildVersions) {
        this.buildVersions = buildVersions;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

}
