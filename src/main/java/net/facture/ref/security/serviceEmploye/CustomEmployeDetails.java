package net.facture.ref.security.serviceEmploye;

import net.facture.ref.gestionEmployee.model.Employee;
import net.facture.ref.gestionEmployee.model.Responsable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomEmployeDetails implements UserDetails {



    private static final long serialVersionUID = 1L;

    public CustomEmployeDetails(Employee employee) {
        this.employee = employee;
    }

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(employee.getRoleEmp()));
    }

    @Override
    public String getPassword() {
        return employee.getMot_de_passe();
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
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