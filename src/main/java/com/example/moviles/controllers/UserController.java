package com.example.moviles.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.moviles.domain.MyUserDetailsService;
import com.example.moviles.domain.UserDTO;
import com.example.moviles.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("user")
public class UserController {

    private final MyUserDetailsService userService;

    public UserController(MyUserDetailsService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<Response> register(@RequestBody UserDTO userDTO) {
        Response response = Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(OK)
                .reason("")
                .developerMessage("registering new user...")
                .build();
        try {
            UserDetails user = userService.registerNewUser(userDTO);
            userService.loadUserByUsername(user.getUsername());
            response.setMessage("Registrado con exito");
        } catch (Exception e) {
            response.setMessage(e.toString());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            UserDetails user = userService.loadUserByUsername(username);
            String access_token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)))
                    .withIssuer(request.getRequestURL().toString())
                    .withArrayClaim("roles",
                            user
                                    .getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()).toArray(new String[0]))
                    //si todo ha salido bien tengo un token para el usuario
                    .sign(algorithm);
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
