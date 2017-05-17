package com.bug.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text" ,nullable = false)
    private String text;
    @Column(name = "added_by" ,nullable = false)
    private String addedBy;
    @Column(name = "added_date" ,nullable = false)
    private String addedDate;
//    @ManyToOne
//    @JoinColumn(name="id",
//            insertable=false, updatable=false,
//            nullable=false)
//    private Issue issue;
    @Column(name = "issue_id" ,nullable = false)
    private int issueId;


    public Comment() {
    }

    public Comment(String text, String addedBy, String addedDate, int issueId) {
        this.text = text;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
        this.issueId = issueId;
    }

/*    public Comment(String text, String addedBy, String addedDate) {
        this.text = text;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

//    public Issue getIssue() {
//        return issue;
//    }
//
//    public void setIssue(Issue issue) {
//        this.issue = issue;
//    }
}
