package com.ems.pecheems.ServicesInterfaces;

import com.ems.pecheems.DTO.MemberDTO;

import java.util.List;
import java.util.Map;

public interface IMemberService {
    Map<String,String> login(String username, String password);
    List<MemberDTO> getAllMembers();
    List<MemberDTO> getAttendMembers();
    MemberDTO accepte(int memberId);
    MemberDTO refuse(int memberId);
}

