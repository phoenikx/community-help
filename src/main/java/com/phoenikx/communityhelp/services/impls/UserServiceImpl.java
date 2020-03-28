package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.BearerToken;
import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.repositories.UserRepository;
import com.phoenikx.communityhelp.services.apis.BearerTokenService;
import com.phoenikx.communityhelp.services.apis.OTPService;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private OTPService otpService;
    @Autowired private UserRepository userRepository;
    @Autowired private BearerTokenService bearerTokenService;

    private static final String NEW_USER_NAME = "User";

    private User createNewUser(String phoneNumber, String userName) {
        return User.builder()
                .name(userName)
                .phoneNumber(phoneNumber)
                .build();
    }

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
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (!userOptional.isPresent()) {
            User user = createNewUser(phoneNumber, NEW_USER_NAME);
            userRepository.save(user);
            String bearerToken = bearerTokenService.generateToken(user.getPhoneNumber(), user.getId());
            return BearerTokenBO.builder()
                    .newUser(true)
                    .token(bearerToken)
                    .build();
        }
        User user = userOptional.get();
        String bearerToken = bearerTokenService.generateToken(user.getPhoneNumber(), user.getId());
        return BearerTokenBO.builder()
                .newUser(false)
                .token(bearerToken)
                .build();

    }
}
