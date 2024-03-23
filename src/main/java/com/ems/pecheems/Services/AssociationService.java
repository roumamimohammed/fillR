package com.ems.pecheems.Services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.stream.Collectors;

import com.ems.pecheems.DTO.AssociationDTO;
import com.ems.pecheems.Entities.AssociationEntity;
import com.ems.pecheems.Mappers.AssociationMapper;
import com.ems.pecheems.Repositories.AssociationRepository;
import com.ems.pecheems.ServicesInterfaces.IAssociationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;

@Service
@Transactional
public class AssociationService implements IAssociationService {
    private final AssociationRepository associationRepository;

    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    @Override
    public AssociationDTO addAssociation(AssociationDTO associationDTO) {
        AssociationEntity associationEntity = AssociationMapper.INSTANCE.toEntity(associationDTO);
        LocalDate startDate = associationEntity.getDate();
        Optional<AssociationEntity> existingCompetition = associationRepository.findAllByDate(startDate);

        if (existingCompetition.isPresent()) {
            System.out.println("Une compétition existe déjà à la même date.");
        } else {
            AssociationEntity savedCompetition = associationRepository.save(associationEntity);
            return AssociationMapper.INSTANCE.toDtoPage(savedCompetition);
        }
        return associationDTO;
    }



    @Override
    public Page<AssociationDTO> getAssociations(int pagenumber, int pagesize) {
        PageRequest pageRequest = PageRequest.of(pagenumber, pagesize);

        LocalDate currentDate = LocalDate.now();
        Page<AssociationEntity> competitionEntities = associationRepository.findAllByDateAfter(currentDate, pageRequest);

        return competitionEntities.map(AssociationMapper.INSTANCE::toDtoPage);
    }

    @Override
    public List<AssociationDTO> getAssociation() {
        LocalDate currentDate = LocalDate.now();

        List<AssociationEntity> competitionEntityOptional = associationRepository.findAllByDateEquals(currentDate);

        return competitionEntityOptional.stream()
                .map(AssociationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }



}
