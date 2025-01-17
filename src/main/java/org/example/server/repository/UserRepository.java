package org.example.server.repository;



import org.example.server.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findUserById(Integer userId);
}
