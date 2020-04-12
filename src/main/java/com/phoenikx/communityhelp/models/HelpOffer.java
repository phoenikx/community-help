package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "help-offers")
@Builder
public class HelpOffer extends BaseModel<String> {
    @Id
    private String helpId;
    private String message;
    private String postId;
    private String helperId;
    private String helperPhoneNumber;
    private GeoJsonPoint helperLocation;
    private String helperName;
}
