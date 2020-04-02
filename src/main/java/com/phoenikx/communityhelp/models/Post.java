package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "posts")
@Builder
public class Post extends BaseModel<String>{
    @Id
    private String id;
    @Indexed
    private String posterId;

    private String title;
    private String description;
    private boolean willingToPay;
    private double latitude;
    private double longitude;
    private String locationDisplayName;
    private String fullAddress;
    private String geoHash;
}
