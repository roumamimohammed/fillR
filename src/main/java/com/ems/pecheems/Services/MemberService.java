package com.ems.pecheems.Services;

import com.ems.pecheems.Enums.Status;
import com.ems.pecheems.ServicesInterfaces.IMemberService;
import org.springframework.security.core.GrantedAuthority;
import com.ems.pecheems.DTO.MemberDTO;
import com.ems.pecheems.Entities.AppUser;
import com.ems.pecheems.Mappers.MemberMapper;
import com.ems.pecheems.Repositories.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ems.pecheems.Enums.Status.*;

@Service
@Transactional
public class MemberService implements IMemberService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository,JwtEncoder jwtEncoder,AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.memberRepository = memberRepository;
    }
    @Override
    public Map<String,String> login(String username, String password) {
        AppUser user = memberRepository.findByEmail(username);
        if (user != null && user.getStatus()== Accepted) {
            // Proceed with login
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            Instant instant = Instant.now();
            String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuedAt(instant)
                    .expiresAt(instant.plus(1000, ChronoUnit.MINUTES))
                    .subject(username)
                    .claim("scope", scope)
                    .build();
            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS512).build(),
                    jwtClaimsSet);
            String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
            return Map.of("access-token", jwt);
        } else {
            return Map.of("error", "Login not allowed due to user not found or status not being accepted");
        }
    }



    @Override
    public List<MemberDTO> getAllMembers() {
        List<AppUser> members = memberRepository.findAll();
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("USER");

        List<MemberDTO> userMembers = members.stream()
                .filter(member -> member.getAuthorities().contains(userAuthority))
                .map(MemberMapper.INSTANCE::toDto)
                .collect(Collectors.toList());

        return userMembers;
    }

    @Override
    public List<MemberDTO> getAttendMembers() {
        List<AppUser> members = memberRepository.findAllByStatus(EnAttend);
        return members.stream()
                .map(MemberMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO accepte(int memberId) {
        int member = memberRepository.updateStatut(memberId,Accepted);
        return MemberMapper.INSTANCE.toDtoint(member);
    }

    @Override
    public MemberDTO refuse(int memberId) {
        int member = memberRepository.updateStatut(memberId,Refuser);
        return MemberMapper.INSTANCE.toDtoint(member);
    }
}
