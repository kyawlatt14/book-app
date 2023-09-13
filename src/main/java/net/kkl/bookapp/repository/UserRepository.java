package net.kkl.bookapp.repository;

import net.kkl.bookapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    Optional<User> findByUsernameIgnoreCaseLike(String username);
}
