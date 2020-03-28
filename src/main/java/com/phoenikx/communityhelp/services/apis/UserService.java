package com.phoenikx.communityhelp.services.apis;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.models.OTP;

public interface UserService {
    OTP initiateLogin(String phoneNumber, int otpLength);
    BearerTokenBO verifyLogin(String requestId, String otpCode);
}
