package com.codegym.qltcbe.log;

import com.codegym.qltcbe.model.entity.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;

public class AuditTrailListener {
    private static Log log = LogFactory.getLog(AuditTrailListener.class);

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Category category) {
        if (category.getId() == 0) {
            log.info("[USER AUDIT] About to add a user");
        } else {
            log.info("[USER AUDIT] About to update/delete user: " + category.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Category category) {
        log.info("[USER AUDIT] add/update/delete complete for category: " + category.getId());
    }

    @PostLoad
    private void afterLoad(Category category) {
        log.info("[USER AUDIT] category loaded from database: " + category.getId());
    }
}
