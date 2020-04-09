package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.config.UserContextStore;
import com.phoenikx.communityhelp.models.Post;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.repositories.PostRepository;
import com.phoenikx.communityhelp.services.apis.AuthService;
import com.phoenikx.communityhelp.services.apis.PostService;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired private UserContextStore userContextStore;
    @Autowired private PostRepository postRepository;
    @Autowired private AuthService authService;
    @Autowired private UserService userService;

    @Override
    public Post createPost(String title, String description, String fullAddress, String geoHash, double latitude,
                           double longitude, String locationDisplayName, boolean willingToPay) {
        Post post = Post.builder()
                .title(title)
                .description(description)
                .fullAddress(fullAddress)
                .geoHash(geoHash)
                .location(new GeoJsonPoint(latitude, longitude))
                .locationDisplayName(locationDisplayName)
                .willingToPay(willingToPay)
                .build();

        String userId = userContextStore.getUserId();
        post.setPosterId(userId);

        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsNearCurrentUser(int pageNum, int pageSize, int distance, boolean includeOwn) {
        String userId = userContextStore.getUserId();
        Optional<User> userOptional = userService.findByUserId(userId);
        if (userOptional.isPresent()) {
            if (includeOwn) {
                return postRepository.findByLocationNear(
                        new Point(userOptional.get().getHomeLocation()), new Distance(distance),
                        PageRequest.of(pageNum, pageSize));
            }
            return postRepository.findByLocationNearAndPosterIdNot(new Point(userOptional.get().getHomeLocation()), userId,
                    new Distance(distance), PageRequest.of(pageNum, pageSize));
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Post> getPost(String postId) {
        return postRepository.findById(postId);
    }

}
