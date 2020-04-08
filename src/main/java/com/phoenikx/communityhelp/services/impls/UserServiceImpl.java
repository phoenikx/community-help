package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.repositories.UserRepository;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createNewUser(String phoneNumber, String userName) {
        User user = User.builder()
                .name(userName)
                .userId(phoneNumber)
                .phoneNumber(phoneNumber)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByPhoneNumber(userId);
    }

    @Override
    public Optional<User> updateUser(String userId, Point location, String name) {
        Optional<User> userOptional = findByUserId(userId);
        if (!userOptional.isPresent())
            return Optional.empty();

        User user = userOptional.get();
        user.setHomeLocation(new GeoJsonPoint(location.getX(), location.getY()));
        user.setName(name);
        userRepository.save(user);

        return Optional.of(user);
    }
}
