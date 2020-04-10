package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "otps")
@Data
@Builder
public class OTP extends BaseModel<String>{
    @Id
    private String id;
    @Indexed
    private String phoneNumber;
    private String otpCode;
    @Indexed
    private String requestId;
    private long expirationTime;
    private int numTriesAllowed;
    private int numAttemptsDone;
}
