package com.ems.pecheems.Controllers;

import com.ems.pecheems.DTO.MemberDTO;
import com.ems.pecheems.Services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/update-statutA/{MemberId}")
    public ResponseEntity<Void> updateStatutA(@PathVariable int MemberId) {
        memberService.accepte(MemberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/update-statutR/{MemberId}")
    public ResponseEntity<Void> updateStatutR(@PathVariable int MemberId) {
        memberService.refuse(MemberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('SCOPE_JURER')")
    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> allMembers = memberService.getAllMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/enattend")
    public ResponseEntity<List<MemberDTO>> getAttendMembers() {
        List<MemberDTO> allMembers = memberService.getAttendMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }
}
