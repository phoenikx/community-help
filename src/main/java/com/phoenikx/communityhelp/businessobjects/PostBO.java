package com.phoenikx.communityhelp.businessobjects;

import com.phoenikx.communityhelp.models.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostBO {
    private String postId;
    private String title;
    private String description;
    private boolean willingToPay;
    private double latitude;
    private double longitude;
    private String locationDisplayName;
    private String fullAddress;
    private String geoHash;

    public static PostBO fromPost(Post post) {
        return PostBO.builder()
                .postId(post.getId())
                .description(post.getDescription())
                .fullAddress(post.getFullAddress())
                .geoHash(post.getGeoHash())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .locationDisplayName(post.getLocationDisplayName())
                .title(post.getTitle())
                .willingToPay(post.isWillingToPay())
                .build();
    }
}
