package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.config.UserContextStore;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.HelpOffer;
import com.phoenikx.communityhelp.models.Post;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.repositories.HelpOfferRepository;
import com.phoenikx.communityhelp.services.apis.HelpOfferService;
import com.phoenikx.communityhelp.services.apis.PostService;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HelpOfferServiceImpl implements HelpOfferService {
    @Autowired private UserContextStore userContextStore;
    @Autowired private UserService userService;
    @Autowired private PostService postService;
    @Autowired private HelpOfferRepository helpOfferRepository;

    private boolean isOwnerOfPost(Post post, String userId) {
        return post.getPosterId().equals(userId);
    }

    @Override
    public HelpOffer createHelpOffer(String postId, String message) {
        String userId = userContextStore.getUserId();
        Optional<User> userOptional = userService.findByUserId(userId);
        Optional<Post> postOptional = postService.getPost(postId);
        if (userOptional.isPresent() && postOptional.isPresent()) {
            Post post = postOptional.get();
            if (post.getPosterId().equals(userId)) {
                throw new InvalidRequestException("Cannot offer help on your own post.");
            }
            HelpOffer helpOffer = HelpOffer.builder()
                    .helper(userOptional.get())
                    .message(message)
                    .postId(postId)
                    .build();
            return helpOfferRepository.save(helpOffer);
        }
        return null;
    }

    @Override
    public List<HelpOffer> getHelpOffersForPost(String postId) {
        Optional<Post> postOptional = postService.getPost(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (!isOwnerOfPost(post, userContextStore.getUserId())) {
                throw new InvalidRequestException("Cannot fetch help offers for posts that are not of logged in user.");
            }
            return helpOfferRepository.findByPostId(postId, Sort.by(Sort.Direction.DESC, "createdAt"));
        }
        return new ArrayList<>();
    }
}
