package net.facture.ref.security.serviceRespo;

import net.facture.ref.gestionEmployee.model.Responsable;
import net.facture.ref.gestionEmployee.repository.RespoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomRespoDetailsService implements UserDetailsService {
    @Autowired
    private RespoRepository respo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Responsable res=respo.findByUsername(username);
        if(res ==null) {
            throw new UsernameNotFoundException("Responsable Not Found");
        }
        return new CustomRespoDetails(res);
    }
}
