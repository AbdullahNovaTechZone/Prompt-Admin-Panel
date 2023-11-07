package com.novatechzone.web.repository;

import com.novatechzone.web.model.User;
import com.novatechzone.web.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findOneByEmailIgnoreCaseAndPassword(String email, String password);

    List<User> findAllByUserRole(UserRole userRole);

    List<User> findTop5ByUserRoleOrderByIdDesc(UserRole userRole);
}
