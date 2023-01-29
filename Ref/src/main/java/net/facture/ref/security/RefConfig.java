package net.facture.ref.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class RefConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("customEmployeDetailService")
    @Autowired
    private UserDetailsService userEmp ;

    @Qualifier("customRespoDetailsService")
    @Autowired
    private UserDetailsService userRespo;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userEmp);
        auth.userDetailsService(userRespo);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/")

                .permitAll()
                .antMatchers("/RespoHome")
                .hasAuthority("ADMIN")
                .antMatchers("/gererFS")
                .hasAuthority("ADMIN")
                .antMatchers("/gererFC")
                .hasAuthority("ADMIN")
                .antMatchers("/ajouterEmp")
                .hasAuthority("ADMIN")
                .antMatchers("/gain")
                .hasAuthority("ADMIN")
                .antMatchers("/EmployeHome")
                .hasAuthority("USER")
                .antMatchers("/AjouterFC")
                .hasAuthority("USER")
                .antMatchers("/gererClient")
                .hasAuthority("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()

                .and()

                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll() ;


    }
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
    @Bean
   public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
        // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
        return super.authenticationManagerBean();
    }

}
