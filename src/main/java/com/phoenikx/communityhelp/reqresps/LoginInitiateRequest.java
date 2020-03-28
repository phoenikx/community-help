package com.phoenikx.communityhelp.reqresps;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LoginInitiateRequest {
    @NotNull
    private String phoneNumber;
    @Min(value = 4)
    @Max(value = 8)
    private int otpLength;
}
