package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.services.apis.AuthService;
import com.phoenikx.communityhelp.services.apis.BearerTokenService;
import com.phoenikx.communityhelp.services.apis.OTPService;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired private OTPService otpService;
    @Autowired private BearerTokenService bearerTokenService;
    @Autowired private UserService userService;
    private static final String NEW_USER_NAME = "User";


    @Override
    public OTP initiateLogin(String phoneNumber, int otpLength) {
        return otpService.generateOTP(phoneNumber, otpLength);
    }

    @Override
    public BearerTokenBO verifyLogin(String requestId, String otpCode) {
        Optional<OTP> otpOptional = otpService.verifyOTP(requestId, otpCode);
        if (!otpOptional.isPresent())
            throw new InvalidRequestException("Invalid otp code.");

        String phoneNumber = otpOptional.get().getPhoneNumber();
        Optional<User> userOptional = userService.findByUserId(phoneNumber);
        if (!userOptional.isPresent()) {
            User user = userService.createNewUser(phoneNumber, NEW_USER_NAME);
            String bearerToken = bearerTokenService.generateToken(user.getPhoneNumber(), user.getUserId());
            return BearerTokenBO.builder()
                    .newUser(true)
                    .token(bearerToken)
                    .build();
        }
        User user = userOptional.get();
        String bearerToken = bearerTokenService.generateToken(user.getPhoneNumber(), user.getUserId());
        return BearerTokenBO.builder()
                .newUser(false)
                .token(bearerToken)
                .build();

    }
}
