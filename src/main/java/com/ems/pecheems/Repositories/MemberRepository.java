package com.ems.pecheems.Repositories;

import com.ems.pecheems.Entities.AppUser;
import com.ems.pecheems.Enums.Status;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<AppUser, Long> {

    @Modifying
    @Query("UPDATE AppUser o SET o.status = :nouveauStatut WHERE o.id = :Id")
    int updateStatut(@Param("Id") int Id, @Param("nouveauStatut") Status nouveauStatut);

    AppUser findByEmail(String username);

    boolean existsByEmail(String numeroAdhesion);

    List<AppUser> findAllByStatus(Status status);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertIntoUsersRoles(int userId, int roleId);
}
