package com.codegym.qltcbe.model.entity;

import com.codegym.qltcbe.log.AuditTrailListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditTrailListener.class)
public class Category {
    private static Log log = LogFactory.getLog(Category.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private AppUser appUser;

    @Column(columnDefinition = "int default 1")
    private int status;

    public Category(String name, AppUser appUser, int status) {
        this.name = name;
        this.appUser = appUser;
        this.status = status;
    }

    @PrePersist
    public void logNewCategoryAttempt() {
        log.info("Attempting to add new cate with: " + name);
    }

    @PostPersist
    public void logNewCategoryAdded() {
        log.info("Added category '" + name + "' with ID: " + id);
    }

    @PreRemove
    public void logCategoryRemovalAttempt() {
        log.info("Attempting to delete category: " + name);
    }

    @PostRemove
    public void logCategoryRemoval() {
        log.info("Deleted category: " + name);
    }

    @PreUpdate
    public void logCategoryUpdateAttempt() {
        log.info("Attempting to update category: " + name);
    }

    @PostUpdate
    public void logUserUpdate() {
        log.info("Updated user: " + name);
    }
}
