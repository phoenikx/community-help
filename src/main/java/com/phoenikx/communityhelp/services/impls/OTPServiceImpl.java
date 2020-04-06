package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.models.OTP;
import com.phoenikx.communityhelp.repositories.OTPRepository;
import com.phoenikx.communityhelp.services.apis.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OTPServiceImpl implements OTPService {
    private static long VALIDITY_DURATION = TimeUnit.MINUTES.toMillis(15);
    private static int ATTEMPTS_ALLOWED = 5;

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
                .expirationTime(System.currentTimeMillis() + VALIDITY_DURATION)
                .numTriesAllowed(ATTEMPTS_ALLOWED)
                .numAttemptsDone(0)
                .build();
        return otpRepository.save(otp);
    }

    @Override
    public Optional<OTP> verifyOTP(String requestId, String otpCode) {
        Optional<OTP> otpOptional = otpRepository.findByRequestId(requestId);

        if (!otpOptional.isPresent()) {
            return Optional.empty();
        }

        OTP otp = otpOptional.get();
        if (System.currentTimeMillis() > otp.getExpirationTime()
                || otp.getNumAttemptsDone() >= otp.getNumTriesAllowed() || !otp.getOtpCode().equals(otpCode)) {
            return Optional.empty();
        }
        otp.setNumAttemptsDone(otp.getNumAttemptsDone()+1);
        otpRepository.save(otp);
        return otpOptional;
    }
}
