package com.bug.dao.spring;

import com.bug.model.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentDao extends CrudRepository<Attachment, Integer> {
    Iterable<Attachment> findAttachmentsByIssueId(int issueId);

    void deleteByName(String name);
}
