package com.phoenikx.communityhelp.businessobjects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BearerTokenBO {
    private String token;
    private boolean newUser;
}
