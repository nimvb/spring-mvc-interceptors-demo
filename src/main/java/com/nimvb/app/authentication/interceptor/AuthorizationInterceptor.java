package com.nimvb.app.authentication.interceptor;

import com.nimvb.app.authentication.model.Token;
import com.nimvb.app.authentication.model.User;
import com.nimvb.app.authentication.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Bearer {jwt}
        if(!authorizationValue.startsWith("Bearer ")){
            return false;
        }
        String[] parts = authorizationValue.split("Bearer ");
        if(parts.length != 2){
            return false;
        }
        String jwt = parts[1];
        request.setAttribute("jwt",jwt);
        User user = authorizationService.authorize(Token.of(jwt));
        if(user == null){
            return false;
        }
        user = User.of(user.getEmail(), user.getFirstName(), user.getLastName(), null);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null, List.of()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
