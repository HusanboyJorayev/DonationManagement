package com.example.donationmanagement.auth;



import com.example.donationmanagement.config.JwtService;
import com.example.donationmanagement.dto.ErrorDto;
import com.example.donationmanagement.token.Token;
import com.example.donationmanagement.token.TokenRepository;
import com.example.donationmanagement.token.TokenType;
import com.example.donationmanagement.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthValidation authValidation;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthenticationResponse signIn(RegisterRequest request) {

        List<ErrorDto> errors = this.userValidation.validate(request);
        if (!errors.isEmpty()) {
            return null;
        }

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
        UserDto dto=this.userMapper.toDto(user);
        this.userRepository.save(user);
        var jwt = jwtService.generateToken(dto);


        return AuthenticationResponse.builder()
                .token("Successful")
                .build();
    }

    public UserDto signUp(AuthenticateRequest request) {
        List<ErrorDto> errors = this.authValidation.validate(request);
        if (!errors.isEmpty()) {
            return null;
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        UserDto dto=this.userMapper.toDto(user);
        var jwt = jwtService.generateToken(dto);
        return dto;
    }

}
