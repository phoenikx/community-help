package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.PostBO;
import com.phoenikx.communityhelp.models.Post;
import com.phoenikx.communityhelp.reqresps.PostCreationRequest;
import com.phoenikx.communityhelp.services.apis.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @Autowired PostService postService;

    @PostMapping
    public PostBO createPost(@RequestBody PostCreationRequest request) {
        Post post = postService.createPost(request.getTitle(), request.getDescription(), request.getFullAddress(),
                request.getGeoHash(), request.getLatitude(), request.getLongitude(), request.getLocationDisplayName(),
                request.isWillingToPay());
        return PostBO.fromPost(post);
    }

    @GetMapping
    public List<PostBO> getPostsNearUser(@RequestParam("pageNo") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<Post> posts = postService.getPostsNearCurrentUser(pageNum, pageSize);
        return posts.stream().map(PostBO::fromPost).collect(Collectors.toList());
    }
}
