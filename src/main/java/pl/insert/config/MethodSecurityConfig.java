package pl.insert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import pl.insert.security.CustomMethodSecurityExpressionHandler;

import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    public void setMethodSecurityExpressionHandler(List<MethodSecurityExpressionHandler> handlers) {
        super.setMethodSecurityExpressionHandler(handlers);
    }

    @Override
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return methodSecurityExpressionHandler();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler();
    }
}
