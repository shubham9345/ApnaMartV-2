package com.ApnaMart.demo.Controller;

import com.ApnaMart.demo.Model.ErrorResponse;
import com.ApnaMart.demo.Model.UserInfo;
import com.ApnaMart.demo.Security.JwtRequest;
import com.ApnaMart.demo.Security.JwtResponse;
import com.ApnaMart.demo.Security.JwtUtil;
import com.ApnaMart.demo.Service.CustomUserDetailService;
import com.ApnaMart.demo.Service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static Utility.ConstantUtil.INVALID_CREDENTIAL;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<UserInfo> Signup(@RequestBody UserInfo userInfo) {
        try {
            if (userInfo.getPassword() == null || userInfo.getUsername() == null) {
                throw new RuntimeException(INVALID_CREDENTIAL);

            }
            if (userInfo.getPassword().equals(" ") || userInfo.getUsername().equals(" ") || userInfo.getPassword().isEmpty() || userInfo.getUsername().isEmpty()) {
                throw new RuntimeException(INVALID_CREDENTIAL);
            }
            if (userInfo.getRoles() == null) {
                userInfo.setRoles("User");
            }
            UserInfo newUser = userInfoService.AddUser(userInfo);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/error")
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid username and password or your token is expired");
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(INVALID_CREDENTIAL);
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUsername());
        String token = this.jwtUtil.generateToken(userDetails.getUsername());
        JwtResponse response = new JwtResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

