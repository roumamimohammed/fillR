package com.ems.pecheems.DTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssociationDTO {
        private int id;
        private LocalDate date;
        private String Nom;
        private String lieu;
        private String Domaine;
        private String Description;
        private String nom_de_president;
        private String email;
        private int Tele;
}
