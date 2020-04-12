package com.phoenikx.communityhelp.config;

import com.phoenikx.communityhelp.services.apis.BearerTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserContextFilter implements Filter {
    @Autowired
    private BearerTokenService bearerTokenService;
    @Autowired
    private UserContextStore userContextStore;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private Pattern authorizationPattern = Pattern.compile("^Bearer (.*)");

    private Optional<String> getAuthorizationToken(String authorizationHeader) {
        Matcher matcher = authorizationPattern.matcher(authorizationHeader);
        if (matcher.matches())
            return Optional.of(matcher.group(1));
        return Optional.empty();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (StringUtils.isEmpty(request.getHeader(AUTHORIZATION_HEADER))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        Optional<String> tokenOptional = getAuthorizationToken(request.getHeader(AUTHORIZATION_HEADER));
        if (!tokenOptional.isPresent()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        try {
            String userId = bearerTokenService.verifyTokenAndGetSubject(tokenOptional.get());
            this.userContextStore.setUserId(userId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            this.userContextStore.clear();
        }
    }
}
