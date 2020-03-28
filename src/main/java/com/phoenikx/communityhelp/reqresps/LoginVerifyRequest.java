package com.phoenikx.communityhelp.reqresps;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVerifyRequest {
    @NotNull
    private String otpCode;
    @NotNull
    private String requestId;
}
