package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataImpl implements UserData {

    private final UserRepository userRepository;
    @Override
    public User save(User user) {
        if(Objects.isNull(user))throw new IllegalArgumentException("User can not be null");
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        if(Objects.isNull(login))throw new IllegalArgumentException("Login can not be null");
        return userRepository.findByLogin(login);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())throw new NoSuchElementException("User not found");
        return optionalUser.get();
    }
}
