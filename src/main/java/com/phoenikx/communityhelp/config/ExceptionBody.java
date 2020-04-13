package com.phoenikx.communityhelp.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionBody {
    private String message;
}
