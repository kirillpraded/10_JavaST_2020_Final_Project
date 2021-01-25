package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 11.01.2021
 * <p>
 * WebFilter for filtering users, that trying to access other user's editing personal info.
 */
@WebFilter(filterName = "UserIdentityFilter")
public class UserIdentityFilter implements Filter {

    /**
     * Method to filter users that trying to access other user's editing personal info.
     *
     * @param req   - client request.
     * @param resp  - response corresponding to the request
     * @param chain - chain of filters.
     * @throws IOException      - when appears servlet's input-output exception.
     * @throws ServletException - exception of the servlet.
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("auth_user");

        if (user.getId().toString().equals(request.getParameter("user_id"))) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
