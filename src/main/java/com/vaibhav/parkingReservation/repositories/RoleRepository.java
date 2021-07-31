package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query(value =  "Select r from Role r where r.role = :role")
    public Role findByRoleName(AssignedRole role);

}
