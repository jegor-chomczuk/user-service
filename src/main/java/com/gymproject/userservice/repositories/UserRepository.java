package com.gymproject.userservice.repositories;

import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getByUserType(UserType userType);
    Optional<User> findByIdAndUserType (Long id, UserType userType);
}
