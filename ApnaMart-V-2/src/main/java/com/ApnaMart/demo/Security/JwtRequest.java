package com.ApnaMart.demo.Security;

import lombok.*;

@Getter
@Setter
public class JwtRequest {
    private String username;
    private String password;
}
