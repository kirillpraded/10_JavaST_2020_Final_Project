package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.util.UserValidator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 18.01.2021
 * <p>
 * WebFilter for filter users without `MODERATOR` role.
 * @see User
 * @see Role
 */
@WebFilter(filterName = "ModeratorAuthorizationFilter")
public class ModeratorAuthorizationFilter implements Filter {

    /**
     * Method to filter users without role `MODERATOR`.
     *
     * @param req   - client request.
     * @param resp  - response corresponding to the request
     * @param chain - chain of filters.
     * @throws IOException      - when appears servlet's input-output exception.
     * @throws ServletException - exception of the servlet.
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("auth_user");
        if (UserValidator.validateRole(user, Role.MODERATOR)) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
