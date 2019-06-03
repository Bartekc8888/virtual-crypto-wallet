package com.politechnika.virtualcryptowallet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.error("Access denied!");

        if (auth != null) {
            log.error("Auth details: ", auth.getDetails());
        }

        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}
