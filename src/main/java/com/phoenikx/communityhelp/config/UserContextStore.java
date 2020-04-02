package com.phoenikx.communityhelp.config;

import lombok.Data;

@Data
public class UserContextStore {
    private String userId;

    public void clear() {
        this.userId = null;
    }
}
