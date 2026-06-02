package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.dto.AuthResponse;
import edu.icet.autocabs.dto.LoginRequest;
import edu.icet.autocabs.dto.RegisterRequest;
import edu.icet.autocabs.entity.User;
import edu.icet.autocabs.entity.UserRole;
import edu.icet.autocabs.repository.UserRepository;
import edu.icet.autocabs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.icet.autocabs.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String registerUser(RegisterRequest request) {

        // Check Email in db
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered!");
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.CUSTOMER); //Default

        // Save db
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {

        // Verify if user exists by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Return success response with a dummy token
        return new AuthResponse(
                token,
                user.getEmail(),
                user.getName(),
                user.getRole().name()
        );
    }

}
