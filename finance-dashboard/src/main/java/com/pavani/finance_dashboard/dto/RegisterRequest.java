package com.pavani.finance_dashboard.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String role; // ADMIN / ANALYST / VIEWER
}
