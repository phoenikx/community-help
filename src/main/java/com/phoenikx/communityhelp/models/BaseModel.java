package com.phoenikx.communityhelp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Getter
@Setter
public class BaseModel<U> {
    @CreatedBy
    private U createdBy;

    @CreatedDate
    @Indexed
    private Date createdDate;

    @LastModifiedBy
    private U lastModifiedBy;

    @LastModifiedDate
    @Indexed
    private Date lastModifiedDate;
}
