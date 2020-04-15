package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.BearerTokenBO;
import com.phoenikx.communityhelp.businessobjects.OTPBO;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.reqresps.LoginInitiateRequest;
import com.phoenikx.communityhelp.reqresps.LoginVerifyRequest;
import com.phoenikx.communityhelp.services.apis.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CrossOrigin
public class AuthController {
    Pattern pattern = Pattern.compile("^\\+91[6-9]\\d{9}$");
    @Autowired
    private AuthService authService;

    private void validatePhoneNumber(String phoneNumber) {
        if (!pattern.matcher(phoneNumber).matches())
            throw new InvalidRequestException("Invalid phone number format.");
    }

    @PostMapping(path = "/login")
    public OTPBO initiateLogin(@RequestBody @Valid LoginInitiateRequest request) {
        validatePhoneNumber(request.getPhoneNumber());
        OTP otp = authService.initiateLogin(request.getPhoneNumber(), request.getOtpLength());
        log.info("OTP: {}", otp);
        return OTPBO.fromOTP(otp);
    }

    @PostMapping(path = "/login/verify")
    public BearerTokenBO verifyLogin(@RequestBody @Valid LoginVerifyRequest request) {
        return authService.verifyLogin(request.getRequestId(), request.getOtpCode());
    }

    @PostMapping(path = "/logout")
    public boolean logout(@RequestHeader("Authorization") String token) {
        return authService.logout(token);
    }
}
