package com.phoenikx.communityhelp.repositories;

import com.phoenikx.communityhelp.models.Post;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HelpRequestRepository extends MongoRepository<Post, String> {
    List<Post> findByLocationNear(Point p, Distance d);
}
