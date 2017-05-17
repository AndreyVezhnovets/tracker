package com.bug.service;

import com.bug.dao.spring.AttachmentDao;
import com.bug.model.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDao attachmentDao;

    public void save(Attachment attachment) {
        attachmentDao.save(attachment);
    }

    public Iterable<Attachment> findAttachmentByIssueId(int issueId) {
        return attachmentDao.findAttachmentsByIssueId(issueId);
    }
    public Attachment findOne(Integer issueId) {
        return attachmentDao.findOne(issueId);
    }

    @Override
    public void delete(String fileName) {
        attachmentDao.deleteByName(fileName);
    }
}
