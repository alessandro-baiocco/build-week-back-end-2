package application.U5D16.repositories;

import application.U5D16.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface  UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
