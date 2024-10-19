package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByLoginAndRole(String login, String role);
}
