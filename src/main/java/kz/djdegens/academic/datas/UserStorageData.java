package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserStorageData {

    public UserStorage save(UserStorage userStorage) {
        if(Objects.isNull(userStorage))throw new IllegalArgumentException("UserStorage can not be null");

        return null;
    }
}
