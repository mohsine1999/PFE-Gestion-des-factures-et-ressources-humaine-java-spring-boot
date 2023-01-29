package net.facture.ref.security.serviceEmploye;


import net.facture.ref.gestionEmployee.model.Employee;
import net.facture.ref.gestionEmployee.repository.EmployeeRepository;
import net.facture.ref.security.serviceEmploye.CustomEmployeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomEmployeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String EMAIL) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(EMAIL);

        if(employee==null) {
            throw new UsernameNotFoundException("user not exits with the name" + EMAIL);
        }return new CustomEmployeDetails(employee) ;

    }
}