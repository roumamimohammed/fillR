package com.ems.pecheems.Entities;

import com.ems.pecheems.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id","role_id"})
            }
    )
    private List<AppRole> appRoles=new ArrayList<>();




    @ManyToMany
    @JoinTable(
            name = "Ranking",
            joinColumns = @JoinColumn(name = "adherent_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private List<AssociationEntity> competitionEntities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.addAll(getAppRoles()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getRoleName()))
                    .toList()
            );
            return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
