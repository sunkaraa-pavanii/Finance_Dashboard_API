package com.pavani.finance_dashboard.controller;

import com.pavani.finance_dashboard.dto.AuthRequest;
import com.pavani.finance_dashboard.dto.RegisterRequest;
import com.pavani.finance_dashboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    //LOGIN
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}


