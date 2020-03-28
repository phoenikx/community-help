package com.phoenikx.communityhelp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "posts")
public class Post extends BaseModel<String>{
    @Id
    private String id;
    private String requesterId;
    private GeoJsonPoint location;
}
