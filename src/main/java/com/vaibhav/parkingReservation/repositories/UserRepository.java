package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    User findByUsername(String username);

    @Query(value = "Select user from User user where user.username = :username and user.email = :email")
    List<User> findByUsernameOrEmail(String username, String email);
}
