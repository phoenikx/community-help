package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.services.apis.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired private OTPService otpService;
    @Autowired private BearerTokenService bearerTokenService;
    @Autowired private UserService userService;
    @Autowired private SMSService smsService;

    @Value("${otp-verification-template}") private String otpVerificationTemplate;
    private static final String NEW_USER_NAME = "User";
    private static final Point DEFAULT_LOCATION = new Point(12.9716, 77.5946);


    @Override
    public OTP initiateLogin(String phoneNumber, int otpLength) {
        OTP otp = otpService.generateOTP(phoneNumber, otpLength);
        String requestId = smsService.sendSMS(phoneNumber, String.format(otpVerificationTemplate, otp.getOtpCode()));
        log.info("Sent sms to phone-number: {}, request id: {}", phoneNumber, requestId);
        return otp;
    }

    @Override
    public BearerTokenBO verifyLogin(String requestId, String otpCode) {
        Optional<OTP> otpOptional = otpService.verifyOTP(requestId, otpCode);
        if (!otpOptional.isPresent())
            throw new InvalidRequestException("Invalid otp code.");

        String phoneNumber = otpOptional.get().getPhoneNumber();
        Optional<User> userOptional = userService.findByUserId(phoneNumber);
        User user = null;
        user = userOptional.orElseGet(() -> userService.createNewUser(phoneNumber, NEW_USER_NAME, DEFAULT_LOCATION));
        String bearerToken = bearerTokenService.generateToken(user.getPhoneNumber(), user.getUserId());
        return BearerTokenBO.builder()
                .detailsUpdated(user.isDetailsUpdated())
                .token(bearerToken)
                .build();

    }

    @Override
    public boolean logout(String token) {
        return bearerTokenService.invalidateToken(token);
    }
}
