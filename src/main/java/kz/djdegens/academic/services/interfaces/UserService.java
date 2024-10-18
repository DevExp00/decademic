package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.UserDto;

public interface UserService {

    ApplicationDto addUser(UserDto userDto);

    ApplicationDto getUser(Long userId);
}
