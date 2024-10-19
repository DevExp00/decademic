package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.User;

public interface UserData {

    User save(User user);

    User findByLogin(String login);

    User findByTelegramIdAndRole(String telegramId, String role);

    User findByLoginAndRole(String login, String role);

    User findById(Long userId);
}
