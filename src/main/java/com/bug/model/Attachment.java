package com.bug.model;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        @Column(name = "multipart_file" ,nullable = false)
        @Lob
        private byte[] multipartFile;
        @Column(name = "name" ,nullable = false, unique = true)
        private String name;
        @Column(name = "added_by" ,nullable = false)
        private String addedBy;
        @Column(name = "added_date" ,nullable = false)
        private String addedDate;
        @Column(name = "issue_id" ,nullable = false)
        private int issueId;
        @Column(name = "type" ,nullable = false)
        private String type;

        public Attachment() {
        }

        public Attachment(byte[] multipartFile, String name, String addedBy, String addedDate, int issueId, String type) {
                this.multipartFile = multipartFile;
                this.name = name;
                this.addedBy = addedBy;
                this.addedDate = addedDate;
                this.issueId = issueId;
                this.type = type;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public byte[] getMultipartFile() {
                return multipartFile;
        }

        public void setMultipartFile(byte[] multipartFile) {
                this.multipartFile = multipartFile;
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

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }
}
