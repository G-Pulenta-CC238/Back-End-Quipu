package gpulenta.quipu.auth.service.impl;

import gpulenta.quipu.auth.model.AuthResponse;
import gpulenta.quipu.auth.model.LoginRequest;
import gpulenta.quipu.auth.model.RegisterRequest;
import gpulenta.quipu.auth.service.AuthService;
import gpulenta.quipu.jwt.JwtService;
import gpulenta.quipu.user.model.Role;
import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        long id = userRepository.findByUsername(request.getUsername()).orElseThrow().getId();
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(user.getId())
                .build();
    }
}
