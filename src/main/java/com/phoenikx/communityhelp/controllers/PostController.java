package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.HelpOfferBO;
import com.phoenikx.communityhelp.businessobjects.PostBO;
import com.phoenikx.communityhelp.models.HelpOffer;
import com.phoenikx.communityhelp.models.Post;
import com.phoenikx.communityhelp.reqresps.PostCreationRequest;
import com.phoenikx.communityhelp.services.apis.HelpOfferService;
import com.phoenikx.communityhelp.services.apis.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "posts", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    private HelpOfferService helpOfferService;

    @PostMapping
    public PostBO createPost(@RequestBody PostCreationRequest request) {
        Post post = postService.createPost(request.getTitle(), request.getDescription(), request.getFullAddress(),
                request.getGeoHash(), request.getLatitude(), request.getLongitude(), request.getLocationDisplayName(),
                request.isWillingToPay());
        return PostBO.fromPost(post);
    }

    @GetMapping
    public List<PostBO> getPostsNearUser(@RequestParam("pageNo") int pageNum, @RequestParam("pageSize") int pageSize,
                                         @RequestParam("radius") int radius,
                                         @RequestParam(value = "includeOwn", required = false, defaultValue = "false") boolean getOwn) {
        List<Post> posts = postService.getPostsNearCurrentUser(pageNum, pageSize, radius, getOwn);
        return posts.stream().map(PostBO::fromPost).collect(Collectors.toList());
    }

    @GetMapping("/me")
    public List<PostBO> getPostsOfLoggedInUser(@RequestParam("pageNo") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        List<Post> posts = postService.getPostsOfLoggedInUser(pageNum, pageSize);
        return posts.stream().map(PostBO::fromPost).collect(Collectors.toList());
    }

    @GetMapping("{postId}/help-offers")
    public List<HelpOfferBO> getHelpOffersForPost(@PathVariable("postId") String postId) {
        List<HelpOffer> helpOffers = helpOfferService.getHelpOffersForPost(postId);
        return helpOffers.stream().map(HelpOfferBO::fromHelpOffer).collect(Collectors.toList());
    }
}
