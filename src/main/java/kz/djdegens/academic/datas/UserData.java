package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserData {

    private final UserRepository userRepository;
    public User save(User user) {
        if(Objects.isNull(user))throw new IllegalArgumentException("User can not be null");
        return userRepository.save(user);
    }

    public User findByLoginAndRole(String login, String role) {
        if(Objects.isNull(login))throw new IllegalArgumentException("Login can not be null");
        if(Objects.isNull(role))throw new IllegalArgumentException("Role can not be null");
        return userRepository.findByLoginAndRole(login,role);
    }

    public User findByLogin(String login) {
        if(Objects.isNull(login))throw new IllegalArgumentException("Login can not be null");
        return userRepository.findByLogin(login);
    }

    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())throw new NoSuchElementException("User not found");
        return optionalUser.get();
    }

    public User findByTelegramIdAndRole(Long telegramId, String role) {
        if(Objects.isNull(telegramId))throw new IllegalArgumentException("telegramId can not be null");
        if(Objects.isNull(role))throw new IllegalArgumentException("Role can not be null");
        return userRepository.findByTelegramIdAndRole(telegramId, role);
    }
}
