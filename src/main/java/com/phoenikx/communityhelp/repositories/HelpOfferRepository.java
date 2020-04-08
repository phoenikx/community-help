package com.phoenikx.communityhelp.repositories;

import com.phoenikx.communityhelp.models.HelpOffer;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HelpOfferRepository extends MongoRepository<HelpOffer, String> {
    List<HelpOffer> findByPostId(String postId, Sort sort);
}
