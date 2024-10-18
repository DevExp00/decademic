package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.UserDto;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.mappers.UserMapper;
import kz.djdegens.academic.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserData userData;
    @Override
    public void addUser(UserDto userDto) {
        if(Objects.isNull(userDto))throw new IllegalArgumentException("User dto can not be null");
        if(Objects.isNull(userDto.getLogin())) throw new IllegalArgumentException("User login can not be null");
        User user = userData.findByLogin(userDto.getLogin());
        if(Objects.isNull(user)){
            userData.save(userMapper.dtoToEntity(userDto));
        }
    }

    @Override
    public ApplicationDto getUser(Long userId) {
        User user = userData.findById(userId);
        UserDto userDto = userMapper.entityToDto(user);
        return ApplicationDto.builder()
                .user(userDto)
                .build();
    }
}
