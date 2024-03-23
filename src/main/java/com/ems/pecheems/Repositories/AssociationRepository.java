package com.ems.pecheems.Repositories;

import com.ems.pecheems.Entities.AssociationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<AssociationEntity, Integer> {
    Optional<AssociationEntity>findById(int id);

    List<AssociationEntity> findAllByDateEquals(LocalDate startDate);

    Optional<AssociationEntity> findAllByDate(LocalDate startDate);

    Page<AssociationEntity> findAllByDateAfter(LocalDate currentDate, PageRequest pageRequest);
}
