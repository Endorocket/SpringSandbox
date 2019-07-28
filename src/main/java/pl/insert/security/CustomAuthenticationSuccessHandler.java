package pl.insert.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.insert.data.User;
import pl.insert.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final ApplicationContext context;

    @Autowired
    public CustomAuthenticationSuccessHandler(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        logger.info("\n\nIn customAuthenticationSuccessHandler\n\n");

        String username = authentication.getName();

        logger.info("username=" + username);

        UserService userService = context.getBean(UserService.class, "userService");

        User user = userService.findByUsername(username);

        // place user in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}