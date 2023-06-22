package com.example.auth.service;

import com.example.auth.domain.User;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    //    public static final String WRONG_PASSWORD = "Неверный пароль";
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    public UserDto getUser(AuthRequestDto authRequestDto) {
//        User user = getUserByLogin(authRequestDto.getLogin());
//
//        if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
//            return new UserDto(user.getLogin());
//        } else {
//            throw new IllegalStateException(WRONG_PASSWORD);
//        }
//    }

    public User getUserByLogin(String login) {
        return userRepository.findById(login)
                .orElseThrow(EntityNotFoundException::new);
    }
}
