package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.config.UserContextStore;
import com.phoenikx.communityhelp.models.Post;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.repositories.PostRepository;
import com.phoenikx.communityhelp.services.apis.PostService;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    @Autowired private UserContextStore userContextStore;
    @Autowired private PostRepository postRepository;
    @Autowired private UserService userService;

    private static final int DISTANCE = 1000;

    @Override
    public Post createPost(String title, String description, String fullAddress, String geoHash, double latitude,
                           double longitude, String locationDisplayName, boolean willingToPay) {
        Post post = Post.builder()
                .title(title)
                .description(description)
                .fullAddress(fullAddress)
                .geoHash(geoHash)
                .latitude(latitude)
                .longitude(longitude)
                .locationDisplayName(locationDisplayName)
                .willingToPay(willingToPay)
                .build();

        String userId = userContextStore.getUserId();
        post.setPosterId(userId);

        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsNearCurrentUser(int pageNum, int pageSize) {
        String userId = userContextStore.getUserId();
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            return postRepository.findByLocationNear(new Point(userOptional.get().getHomeLocation()),
                    new Distance(DISTANCE));
        }
        return new ArrayList<>();
    }

}
