package com.spring.eventPlaning.entity;

import com.spring.eventPlaning.dto.SuperDto;
import com.spring.eventPlaning.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class SuperEntity<D extends SuperDto> {

    //    private static final int HOURS = 5;
    //    private static final int MINUTES = 30;
    @Column(nullable = false, updatable = false)
    @CreatedBy
    private String createdBy = "";
    @LastModifiedBy
    private String modifiedBy;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;

    public abstract D toDto();

    protected void setAuditDetail(D d) {

        d.setCreatedBy(createdBy);
        d.setCreatedDate(createdDate);
        d.setModifiedBy(modifiedBy);
        d.setModifiedDate(modifiedDate);
        d.setRecordStatus(recordStatus);
    }
}

