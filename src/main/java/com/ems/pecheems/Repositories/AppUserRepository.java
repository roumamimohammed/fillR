package com.ems.pecheems.Repositories;

import com.ems.pecheems.Entities.AppRole;
import com.ems.pecheems.Entities.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> getAppUsersByEmail(String Email);

    boolean existsByEmail(String Email);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertIntoUsersRoles(int userId, int roleId);

}
