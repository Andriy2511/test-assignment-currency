package org.example.currency.repository;

import org.example.currency.model.Role;
import org.example.currency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    List<User> findByRole(Role role);
    Optional<User> findUserByLogin(String login);

}
