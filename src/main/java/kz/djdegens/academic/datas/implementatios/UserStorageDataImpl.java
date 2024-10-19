package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.UserStorageData;
import kz.djdegens.academic.entities.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserStorageDataImpl implements UserStorageData {

    @Override
    public UserStorage save(UserStorage userStorage) {
        if(Objects.isNull(userStorage))throw new IllegalArgumentException("UserStorage can not be null");

        return null;
    }
}
