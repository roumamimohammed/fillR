package com.ems.pecheems.DTO;


import com.ems.pecheems.Enums.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemberDTO {
    private int id;
    private Status status;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
