package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private GeoJsonPoint location;
    private String locationDisplayName;
    private String fullAddress;
    private String geoHash;
}
