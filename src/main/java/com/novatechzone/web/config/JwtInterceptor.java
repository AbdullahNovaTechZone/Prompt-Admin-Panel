package com.novatechzone.web.config;

import com.novatechzone.web.dto.RequestMetaDTO;
import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;
    RequestMetaDTO requestMetaDTO;

    public JwtInterceptor(RequestMetaDTO requestMetaDTO) {
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("authorization");
        System.out.println(request.getRequestURI());
        if (
                !(
                        request.getRequestURI().contains("auth/") ||
                                request.getRequestURI().contains("assets/")
                )
        ) {
            Claims claims = jwtUtils.verifyToken(auth);

            requestMetaDTO.setId(Long.valueOf(claims.getIssuer()));
            requestMetaDTO.setName(claims.get("name").toString());
            requestMetaDTO.setEmail(claims.get("email").toString());
            requestMetaDTO.setUserRole(UserRole.valueOf(claims.get("user_role").toString()));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
