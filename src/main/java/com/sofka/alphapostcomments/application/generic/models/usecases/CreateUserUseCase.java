package com.sofka.alphapostcomments.application.generic.models.usecases;

import com.sofka.alphapostcomments.application.adapters.repository.IUserRepository;
import com.sofka.alphapostcomments.application.generic.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<User> save(User user, String role){
        return this.userRepository
                .save(user.toBuilder()
                        .password(passwordEncoder.encode(user.getPassword()))
                        .email(user.getUsername()+"@email.com")
                        .roles(new ArrayList<>(){{add(role);}}).build());
    }
}
