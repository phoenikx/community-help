package com.phoenikx.communityhelp.reqresps;

import lombok.Data;

@Data
public class PostCreationRequest {
    private String title;
    private String description;
    private boolean willingToPay;
    private String posterId;
    private double latitude;
    private double longitude;
    private String locationDisplayName;
    private String fullAddress;
    private String geoHash;
}
