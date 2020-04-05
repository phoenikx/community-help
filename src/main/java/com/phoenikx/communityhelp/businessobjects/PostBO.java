package com.phoenikx.communityhelp.businessobjects;

import com.phoenikx.communityhelp.models.Post;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Builder
public class PostBO {
    private String postId;
    private String title;
    private String description;
    private boolean willingToPay;
    private GeoJsonPoint location;
    private String locationDisplayName;
    private String fullAddress;
    private String geoHash;

    public static PostBO fromPost(Post post) {
        return PostBO.builder()
                .postId(post.getId())
                .description(post.getDescription())
                .fullAddress(post.getFullAddress())
                .geoHash(post.getGeoHash())
                .location(post.getLocation())
                .locationDisplayName(post.getLocationDisplayName())
                .title(post.getTitle())
                .willingToPay(post.isWillingToPay())
                .build();
    }
}
