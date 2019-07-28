package pl.insert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.insert.security.CustomAuthenticationSuccessHandler;
import pl.insert.services.UserService;

@Configuration
@EnableWebSecurity
@ComponentScan({"pl.insert.config", "pl.insert.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext context;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Autowired
    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, ApplicationContext context) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.context = context;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/employees").hasRole("USER")
                .antMatchers("/employees/*").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login-page").successHandler(customAuthenticationSuccessHandler)
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(context.getBean(UserService.class, "userService"));
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
