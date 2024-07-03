package com.bff.reservation.common.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String name;
    private String no_telp;
    private String email;
    private String password;
    private Role role;
    @CreationTimestamp
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<Reservations> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Notifications> notifications;

    @Override
    public int hashCode() {
        return Objects.hash(user_id, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return Objects.equals(user_id, user.user_id) &&
                Objects.equals(email, user.email);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

	@Override
	public String getUsername() {
        return email;
	}

	@Override
	public boolean isAccountNonExpired() {
        return true;
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