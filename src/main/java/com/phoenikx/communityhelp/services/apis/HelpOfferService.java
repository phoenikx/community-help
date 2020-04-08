package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.models.HelpOffer;

import java.util.List;

public interface HelpOfferService {
    HelpOffer createHelpOffer(String postId, String message);

    List<HelpOffer> getHelpOffersForPost(String postId);
}
