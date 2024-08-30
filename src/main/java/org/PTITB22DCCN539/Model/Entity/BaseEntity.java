package org.PTITB22DCCN539.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "createddate", updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private LocalDate createdDate;
    @Column(name = "modifieddate", insertable = false)
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private LocalDate modifiedDate;
    @Column(name = "createdby", updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "modifiedby", insertable = false)
    @LastModifiedBy
    private String modifiedBy;
}
