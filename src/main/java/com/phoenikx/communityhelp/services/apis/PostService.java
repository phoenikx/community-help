package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.models.Post;

import java.util.List;

public interface PostService {
    Post createPost(String title, String description, String fullAddress, String geoHash,
                    double latitude, double longitude, String locationDisplayName, boolean willingToPay);

    List<Post> getPostsNearCurrentUser(int pageNum, int pageSize);
}
