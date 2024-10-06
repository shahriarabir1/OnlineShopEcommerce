package com.RaqamiUniverse.RaqamiOnlineShop.controller.auth;

import com.RaqamiUniverse.RaqamiOnlineShop.request.LoginRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.security.jwtUtils.JwtProviders;
import com.RaqamiUniverse.RaqamiOnlineShop.security.user.UserInformation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtProviders jwtProviders;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login (@Valid @RequestBody LoginRequest loginrequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginrequest.getEmail(), loginrequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProviders.generateToken(authentication);
            UserInformation userdetails = (UserInformation) authentication.getPrincipal();
            return ResponseEntity.ok(new ApiResponse(userdetails.getUsername(), token));
        }
        catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
