package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Para endpoints REST
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("auth")
    public ResponseEntity<GenericResponse<String>> auth(@RequestBody AuthRequest authRequest) {
        GenericResponse<String> response = new GenericResponse<String>();
        try {
            // Autenticar al usuario con Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("Invalid username or password");
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar token JWT
            String jwtToken = jwtTokenUtil.generateToken(authRequest.getEmail());

            // Devolver el token como respuesta
            response.setResult(jwtToken);
            response.setStatus(true);
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            response.setResult("Invalid username or password");
            response.setStatus(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    public static class AuthRequest {
        private String email;
        private String password;

        // Getters y Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
