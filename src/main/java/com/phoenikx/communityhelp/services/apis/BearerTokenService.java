package com.phoenikx.communityhelp.services.apis;

public interface BearerTokenService {
    String generateToken(String phoneNumber, String userId);

    String verifyTokenAndGetSubject(String token);

    boolean invalidateToken(String token);
}
