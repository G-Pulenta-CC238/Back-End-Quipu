package gpulenta.quipu.auth.service;

import gpulenta.quipu.auth.model.AuthResponse;
import gpulenta.quipu.auth.model.LoginRequest;
import gpulenta.quipu.auth.model.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);
}
