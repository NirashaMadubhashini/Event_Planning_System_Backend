package com.spring.eventPlaning.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.eventPlaning.entity.SuperEntity;
import com.spring.eventPlaning.enums.RecordStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class SuperDto<E extends SuperEntity> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy = "System";
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modifiedBy = "System";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdDate = new Date();
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "IST")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date modifiedDate = new Date();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;


    public abstract E toEntity();
}