package com.phoenikx.communityhelp.businessobjects;

import com.phoenikx.communityhelp.models.HelpOffer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Objects;

@Builder
@Data
public class HelpOfferBO {
    private String helpId;
    private String message;
    private String postId;
    private String helperName;
    private GeoJsonPoint helperLocation;
    private String helperPhoneNumber;

    public static HelpOfferBO fromHelpOffer(HelpOffer help) {
        if (Objects.isNull(help)) {
            return null;
        }
        return HelpOfferBO.builder()
                .helpId(help.getHelpId())
                .message(help.getMessage())
                .postId(help.getPostId())
                .helperName(help.getHelper().getName())
                .helperLocation(help.getHelper().getHomeLocation())
                .helperPhoneNumber(help.getHelper().getPhoneNumber())
                .build();
    }
}
