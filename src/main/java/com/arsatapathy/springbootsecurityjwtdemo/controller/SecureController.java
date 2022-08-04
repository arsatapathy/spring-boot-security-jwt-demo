package com.arsatapathy.springbootsecurityjwtdemo.controller;

import com.arsatapathy.springbootsecurityjwtdemo.model.AuthRequest;
import com.arsatapathy.springbootsecurityjwtdemo.model.AuthResponse;
import com.arsatapathy.springbootsecurityjwtdemo.service.JwtUtil;
import com.arsatapathy.springbootsecurityjwtdemo.service.SecureUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecureUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome Home!", HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect credentials.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());

        final String JWT_TOKEN = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthResponse(JWT_TOKEN), HttpStatus.OK);

    }

}
