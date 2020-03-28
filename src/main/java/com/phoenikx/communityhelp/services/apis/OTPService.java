package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.models.OTP;

import java.util.Optional;

public interface OTPService {
    OTP generateOTP(String phoneNumber, int length);
    Optional<OTP> verifyOTP(String requestID, String otpCode);
}
