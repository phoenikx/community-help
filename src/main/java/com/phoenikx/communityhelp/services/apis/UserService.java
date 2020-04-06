package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.models.User;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Optional;

public interface UserService {
    Optional<User> updateUser(String phoneNumber, GeoJsonPoint geoJsonPoint, String name);

    User createNewUser(String phoneNumber, String userName);

    Optional<User> findByUserId(String phoneNumber);
}
