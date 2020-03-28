package com.phoenikx.communityhelp.businessobjects;

import com.phoenikx.communityhelp.models.OTP;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OTPBO {
    private String requestId;

    public static OTPBO fromOTP(OTP otp) {
        return OTPBO.builder()
                .requestId(otp.getRequestId())
                .build();
    }
}
