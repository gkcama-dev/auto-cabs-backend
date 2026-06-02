package edu.icet.autocabs.service;

import edu.icet.autocabs.dto.AuthResponse;
import edu.icet.autocabs.dto.LoginRequest;
import edu.icet.autocabs.dto.RegisterRequest;

public interface UserService {

    String registerUser(RegisterRequest request);
    AuthResponse loginUser(LoginRequest request);

}
