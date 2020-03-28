package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "otps")
@Data
@Builder
public class OTP extends BaseModel<String>{
    private String id;
    private String phoneNumber;
    private String otpCode;
    private String requestId;
}
