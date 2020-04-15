package com.phoenikx.communityhelp.services.apis;

public interface SMSService {
    String sendSMS(String phoneNumber, String message);
}
