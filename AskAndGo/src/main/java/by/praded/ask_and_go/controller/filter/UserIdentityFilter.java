package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.entity.User;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
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
        User user = (User) request.getSession().getAttribute(Attribute.AUTH_USER);

        if (user.getId().toString().equals(request.getParameter(Attribute.USER_ID))) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
