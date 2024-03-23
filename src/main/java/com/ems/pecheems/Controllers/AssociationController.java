package com.ems.pecheems.Controllers;

import com.ems.pecheems.DTO.AssociationDTO;
import com.ems.pecheems.Services.AssociationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/competitions")
public class AssociationController {

    private final AssociationService associationService;

    @Autowired
    public AssociationController(AssociationService AssociationService) {
        this.associationService = AssociationService;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SCOPE_JURER')")
    public ResponseEntity<AssociationDTO> addCompetition(@RequestBody AssociationDTO AssociationDTO) {
        try {
            AssociationDTO addedCompetition = associationService.addAssociation(AssociationDTO);
            return ResponseEntity.ok(addedCompetition);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_JURER')")
    @GetMapping("/now")
    public ResponseEntity<List<AssociationDTO>> getCompetition() {
        List<AssociationDTO> associationDtoOptional = associationService.getAssociation();
            return new ResponseEntity<>(associationDtoOptional, HttpStatus.OK);
        }

    @PreAuthorize("hasAuthority('SCOPE_JURER')")
    @GetMapping
    public Page<AssociationDTO> getCompetitions(@RequestParam("page")int page,@RequestParam("size")int size) {
        return associationService.getAssociations(page,size);
    }

}
