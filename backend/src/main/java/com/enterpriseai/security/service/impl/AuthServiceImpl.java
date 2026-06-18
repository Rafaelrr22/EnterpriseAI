package com.enterpriseai.security.service.impl;


import com.enterpriseai.security.dto.LoginRequest;
import com.enterpriseai.security.dto.LoginResponse;
import com.enterpriseai.security.dto.RegisterRequest;
import com.enterpriseai.security.dto.RegisterResponse;
import com.enterpriseai.security.service.AuthService;
import com.enterpriseai.security.service.CustomUserDetailsService;
import com.enterpriseai.user.repository.RoleRepository;
import com.enterpriseai.user.repository.UserRepository;
import com.enterpriseai.common.exception.ResourceAlreadyExistsException;
import com.enterpriseai.user.entity.Role;
import com.enterpriseai.user.entity.RoleName;
import com.enterpriseai.user.entity.User;
import com.enterpriseai.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "A user with this email already exists."
            );
        }

        Role role = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() ->
                        new IllegalStateException("Default USER role not found.")
                );

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail()
        );

    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token);
    }
}
