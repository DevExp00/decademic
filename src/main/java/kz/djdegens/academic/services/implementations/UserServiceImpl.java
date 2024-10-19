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
    public ApplicationDto checkUser(UserDto userDto) {
        if(Objects.isNull(userDto))throw new IllegalArgumentException("Student dto can not be null");
        if(Objects.isNull(userDto.getLogin())) throw new IllegalArgumentException("Student login can not be null");
        if(Objects.isNull(userDto.getRole()))throw new IllegalArgumentException("Role can not be null");
        User user = userData.findByTelegramIdAndRole(userDto.getTelegramId(), userDto.getRole());
        if(Objects.isNull(user)){
            user = userData.save(userMapper.dtoToEntity(userDto));
        }else {
            user = userData.save(userMapper.dtoToEntity(user, userDto));
        }
        return ApplicationDto.builder()
                .user(UserDto.builder()
                        .id(user.getId())
                        .build())
                .build();
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
