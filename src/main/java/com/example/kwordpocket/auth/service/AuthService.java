package com.example.kwordpocket.auth.service;

import com.example.kwordpocket.auth.dto.SigninRequest;
import com.example.kwordpocket.auth.dto.SignupRequest;
import com.example.kwordpocket.global.jwt.JwtUtil;
import com.example.kwordpocket.user.entity.User;
import com.example.kwordpocket.user.enums.Role;
import com.example.kwordpocket.user.exception.EmailNotFoundException;
import com.example.kwordpocket.user.exception.PasswordNotMatchException;
import com.example.kwordpocket.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequest request) {
        String password = request.getPassword();
        String encodePassword = passwordEncoder.encode(password);

        User user = new User(
                request.getEmail(),
                encodePassword,
                Role.of(request.getRole())
        );

        userRepository.save(user);

    }

    @Transactional
    public String signin(@Valid SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EmailNotFoundException()
        );

        String rawPassword = request.getPassword();
        String encodedPassword = user.getPassword();
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        if (!matches) {
            throw new PasswordNotMatchException();
        }

        return jwtUtil.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

}
