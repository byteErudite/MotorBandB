package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Query(value = "select ur.role.roleId from UserRole ur where ur.user.userId = :userId ")
    public List<UUID> getUserRoleIdbyUserId(UUID userId);

    @Query(value = "select ur from UserRole ur where ur.user.userId = :userId ")
    public List<UserRole> getUserRolebyUserId(UUID userId);

}
