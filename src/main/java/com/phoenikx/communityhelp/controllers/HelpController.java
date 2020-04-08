package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.HelpOfferBO;
import com.phoenikx.communityhelp.models.HelpOffer;
import com.phoenikx.communityhelp.reqresps.HelpOfferCreateRequest;
import com.phoenikx.communityhelp.services.apis.HelpOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "help-offers")
public class HelpController {
    @Autowired
    private HelpOfferService helpOfferService;

    @PostMapping
    public HelpOfferBO offerHelp(@RequestBody HelpOfferCreateRequest request){
        HelpOffer helpOffer = helpOfferService.createHelpOffer(request.getPostId(), request.getMessage());
        return HelpOfferBO.fromHelpOffer(helpOffer);
    }
}
