package com.bug.service;

import com.bug.model.Attachment;

public interface AttachmentService {
    void save(Attachment attachment);

    Iterable<Attachment> findAttachmentByIssueId(int issueId);

    Attachment findOne(Integer issueId);

    void delete(String fileName);
}
