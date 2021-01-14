package com.slbruno.carrental.user.service;

import com.slbruno.carrental.user.dto.AuthenticatedUser;
import com.slbruno.carrental.user.dto.JwtRequest;
import com.slbruno.carrental.user.dto.JwtResponse;
import com.slbruno.carrental.user.entity.User;
import com.slbruno.carrental.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    public JwtResponse createAuthenticationToken(JwtRequest jwtRequest) {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        UserDetails userDetails = this.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenManager.generateToken(userDetails);

        return JwtResponse.builder().jwtToken(token).build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));

        return new AuthenticatedUser(user.getUsername(), user.getPassword(), user.getRole().getDescription());
    }
}
