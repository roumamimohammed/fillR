package com.ems.pecheems.Controllers;

import com.ems.pecheems.DTO.MemberDTO;
import com.ems.pecheems.Entities.AppUser;
import com.ems.pecheems.Enums.Status;
import com.ems.pecheems.Mappers.MemberMapper;
import com.ems.pecheems.Repositories.AppUserRepository;
import com.ems.pecheems.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final MemberService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(MemberService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        MemberDTO newUser = MemberDTO.builder()
                .status(Status.EnAttend)
                .username(registerRequest.getUsername())
                .lastName(registerRequest.getLastName())
                .firstName(registerRequest.getFirstName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        AppUser newUser1 = MemberMapper.INSTANCE.toEntity(newUser);
        userRepository.save(newUser1);
        userRepository.insertIntoUsersRoles(newUser1.getId(), 1);

        return ResponseEntity.ok(newUser);
    }



    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }



}
