package it.eg.cookbook.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class AbstractAuditingEntity {

    private LocalDateTime creationDate;
    private String createdBy;

    private LocalDateTime updatedDate;
    private String updatedBy;

    @PrePersist
    public void onPrePersist() {
        creationDate = LocalDateTime.now();
        createdBy = getUser();
    }

    @PreUpdate
    public void onPreUpdate() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        updatedDate = LocalDateTime.now();
        updatedBy = getUser();
    }

    private String getUser() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}