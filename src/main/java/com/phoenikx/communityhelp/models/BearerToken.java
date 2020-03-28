package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BearerToken {
    private String userId;
    private String phoneNumber;
}
