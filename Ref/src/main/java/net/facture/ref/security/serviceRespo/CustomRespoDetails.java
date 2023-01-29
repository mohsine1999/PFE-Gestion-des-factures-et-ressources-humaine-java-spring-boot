package net.facture.ref.security.serviceRespo;

import net.facture.ref.gestionEmployee.model.Responsable;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

public class CustomRespoDetails implements UserDetails {

    private Responsable responsable;



    public CustomRespoDetails(Responsable responsable) {
        this.responsable = responsable;
    }
//Note
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(responsable.getRoleRes()));
    }


    @Override
    public String getPassword() {
        return responsable.getPassword();
    }

    @Override
    public String getUsername() {
        return responsable.getUsername();
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
