package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.models.User;
import org.springframework.data.geo.Point;

import java.util.Optional;

public interface UserService {
    Optional<User> updateUser(String userId, Point geoJsonPoint, String name);

    User createNewUser(String phoneNumber, String userName);

    Optional<User> findByUserId(String userId);
}
