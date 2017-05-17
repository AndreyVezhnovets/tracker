package com.bug.model;

import javax.persistence.*;

@Entity
@Table(name = "build")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "version", nullable = false, unique = true)
    private String version;

    @Column(name = "project_id")
    private int projectId;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    public Build() {
    }

    public Build(String version) {
        this.version = version;
    }

    public Build(String version, int projectId) {
        this.version = version;
        this.projectId = projectId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
