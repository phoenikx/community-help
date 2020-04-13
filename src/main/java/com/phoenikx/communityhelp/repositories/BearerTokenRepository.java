package com.phoenikx.communityhelp.repositories;

import com.phoenikx.communityhelp.models.InvalidatedToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BearerTokenRepository extends MongoRepository<InvalidatedToken, String> {
}
