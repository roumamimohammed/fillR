package com.ems.pecheems.ServicesInterfaces;

import com.ems.pecheems.DTO.AssociationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAssociationService {
    AssociationDTO addAssociation(AssociationDTO AssociationDTO);

    Page<AssociationDTO> getAssociations(int pageNumber, int pageSize);

    List<AssociationDTO> getAssociation();

}
