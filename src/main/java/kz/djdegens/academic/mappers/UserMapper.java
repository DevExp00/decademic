package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.UserDto;
import kz.djdegens.academic.entities.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    public User dtoToEntity(UserDto userDto){
        if(Objects.isNull(userDto))throw new IllegalArgumentException("User dto can not be null");
        User user = new User();
        user.setFirstName(userDto.getFirstName() == null ? null : userDto.getFirstName());
        user.setLastName(userDto.getLastName() == null ? null : userDto.getLastName());
        user.setLogin(userDto.getLogin() == null ? null : userDto.getLogin());
        user.setRole(userDto.getRole() == null ? null : userDto.getRole());
        return user;
    }

    public UserDto entityToDto(User user){
        if(Objects.isNull(user))throw new IllegalArgumentException("User can not be null");
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName() == null ? null : user.getFirstName());
        userDto.setLastName(user.getLastName() == null ? null : user.getLastName());
        userDto.setLogin(user.getLogin() == null ? null : user.getLogin());
        userDto.setRole(user.getRole() == null ? null : user.getRole());
        return userDto;
    }
}
