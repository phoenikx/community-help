package com.phoenikx.communityhelp.businessobjects;

import com.phoenikx.communityhelp.models.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Builder
@Data
public class UserBO {
    private String name;
    private String userId;
    private String phoneNumber;
    private GeoJsonPoint homeLocation;

    public static UserBO fromUser(User user) {
        return UserBO.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .homeLocation(user.getHomeLocation())
                .build();
    }
}
