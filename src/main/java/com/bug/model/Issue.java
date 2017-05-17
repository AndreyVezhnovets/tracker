package com.bug.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ISSUE")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "create_date", nullable = false)
    private String createDate;
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    @Column(name = "modify_date")
    private String modifyDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "SUMMARY", nullable = false)
    private String summary;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "TYPE", nullable = false)
    private String type;
    @Column(name = "PRIORITY", nullable = false)
    private String priority;
    @Column(name = "PROJECT", nullable = false)
    private String project;
    @Column(name = "BUILD_FOUND")
    private String buildFound;
    @Column(name = "ASSIGNEE")
    private String assignee;
    @Column(name = "RESOLUTION")
    private String resolution;
//    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name="issue_id")
//    private List<Comment> comments;

    public Issue(String summary, String description, String status, String type, String priority, String project) {
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.type = type;
        this.priority = priority;
        this.project = project;
    }

    public Issue(String createDate, String createdBy, String summary, String description, String status, String type, String priority, String project, String buildFound) {
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.type = type;
        this.priority = priority;
        this.project = project;
        this.buildFound = buildFound;
    }

    public Issue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBuildFound() {
        return buildFound;
    }

    public void setBuildVersion(String buildFound) {
        this.buildFound = buildFound;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setBuildFound(String buildFound) {
        this.buildFound = buildFound;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}


