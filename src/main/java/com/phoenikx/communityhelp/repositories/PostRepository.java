package com.phoenikx.communityhelp.repositories;

import com.phoenikx.communityhelp.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByLocationNearAndPosterIdNot(Point p, String posterId, Distance d, Pageable pageable);

    List<Post> findByLocationNear(Point point, Distance distance, Pageable pageable);

    List<Post> findByPosterId(String userId, Pageable pageRequest);
}
