package com.phoenikx.communityhelp.repositories;

import com.phoenikx.communityhelp.models.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OTPRepository extends MongoRepository<OTP, String> {
    Optional<OTP> findByRequestId(String requestId);
}
