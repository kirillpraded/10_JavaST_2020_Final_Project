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
 * 20.12.2020
 * <p>
 * WebFilter for filter users without admin role.
 * @see User
 * @see Role
 */
@WebFilter(filterName = "AdminAuthorizationFilter")
public class AdminAuthorizationFilter implements Filter {

    /**
     * Method to filter non-admin users.
     *
     * @param servletRequest  - client request.
     * @param servletResponse - response corresponding to the request
     * @param chain           - chain of filters.
     * @throws IOException      - when appears servlet's input-output exception.
     * @throws ServletException - exception of the servlet.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("auth_user");
        if (UserValidator.validateRole(user, Role.ADMIN)) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещен");
        }
    }
}
