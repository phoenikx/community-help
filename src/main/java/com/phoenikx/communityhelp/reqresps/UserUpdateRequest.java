package com.phoenikx.communityhelp.reqresps;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {
    @NotNull private String name;
    @NotNull private GeoJsonPoint homeLocation;
}
