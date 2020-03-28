package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.businessobjects.OTPBO;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.reqresps.LoginInitiateRequest;
import com.phoenikx.communityhelp.reqresps.LoginVerifyRequest;
import com.phoenikx.communityhelp.services.apis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    Pattern pattern = Pattern.compile("^[6-9]\\d{9}$");
    @Autowired private UserService userService;

    private void validatePhoneNumber(String phoneNumber) {
        if (!pattern.matcher(phoneNumber).matches())
            throw new InvalidRequestException("Invalid phone number format.");
    }

    @PostMapping(path = "/login")
    public OTPBO initiateLogin(@RequestBody @Valid LoginInitiateRequest request) {
        validatePhoneNumber(request.getPhoneNumber());
        OTP otp = userService.initiateLogin(request.getPhoneNumber(), request.getOtpLength());
        return OTPBO.fromOTP(otp);
    }

    @PostMapping(path = "/login/verify")
    public BearerTokenBO verifyLogin(@RequestBody @Valid LoginVerifyRequest request) {
        return userService.verifyLogin(request.getRequestId(), request.getOtpCode());
    }
}
