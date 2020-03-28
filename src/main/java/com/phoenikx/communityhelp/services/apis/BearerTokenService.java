package com.phoenikx.communityhelp.services.apis;

public interface BearerTokenService {
    String generateToken(String phoneNumber, String userId);
    String verifyToken(String token);
}
