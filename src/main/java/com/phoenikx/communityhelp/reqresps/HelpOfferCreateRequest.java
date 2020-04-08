package com.phoenikx.communityhelp.reqresps;

import lombok.Data;

@Data
public class HelpOfferCreateRequest {
    private String message;
    private String postId;
}
