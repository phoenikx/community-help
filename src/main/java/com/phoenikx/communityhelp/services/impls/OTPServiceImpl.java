package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.repositories.OTPRepository;
import com.phoenikx.communityhelp.services.apis.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OTPServiceImpl implements OTPService {
    @Autowired
    OTPRepository otpRepository;

    private String generateRandomCode(int length) {
        long minNumber = (long)Math.pow(10, length-1);
        long otpCode = minNumber + (long)(Math.random() * 9 * minNumber);
        return String.valueOf(otpCode);
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public OTP generateOTP(String phoneNumber, int length) {
        OTP otp = OTP.builder()
                .otpCode(generateRandomCode(length))
                .phoneNumber(phoneNumber)
                .requestId(generateRequestId())
                .build();
        return otpRepository.save(otp);
    }

    @Override
    public Optional<OTP> verifyOTP(String requestId, String otpCode) {
        Optional<OTP> otpOptional = otpRepository.findByRequestId(requestId);
        if (otpOptional.isPresent() && otpOptional.get().getOtpCode().equals(otpCode))
            return otpOptional;

        return Optional.empty();
    }
}
