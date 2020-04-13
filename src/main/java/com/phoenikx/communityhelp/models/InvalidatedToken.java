package com.phoenikx.communityhelp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invalidated_tokens")
@Data
@Builder
public class InvalidatedToken {
    @Id
    private String token;
}
