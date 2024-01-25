package com.example.donationmanagement.auth;

import com.example.donationmanagement.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> authenticate(@RequestBody @Valid AuthenticateRequest request) {

        return ResponseEntity.ok(authenticationService.signUp(request));
    }

}
