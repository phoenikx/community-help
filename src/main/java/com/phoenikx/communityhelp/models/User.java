package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "users")
public class User extends BaseModel<String> {
    @Id
    private String userId;
    private String name;
    @Indexed(unique = true)
    private String phoneNumber;
    private GeoJsonPoint homeLocation;
    private boolean detailsUpdated;
}
