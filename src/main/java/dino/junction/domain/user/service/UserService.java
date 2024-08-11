package dino.junction.domain.user.service;

import dino.junction.common.error.CustomException;
import dino.junction.domain.user.dto.UserRequest;
import dino.junction.domain.user.dto.UserResponse;
import dino.junction.domain.user.entity.User;
import dino.junction.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return UserResponse.of(user);
    }
    @Transactional
    public void changeUser(final Long userId, final UserRequest userRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.update(userRequest);
        userRepository.save(user);
    }
}
