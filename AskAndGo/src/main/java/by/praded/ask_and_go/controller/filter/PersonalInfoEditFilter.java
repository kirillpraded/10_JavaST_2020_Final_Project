package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 12.01.2021
 * <p>
 * WebFilter for filtering users, that trying to access other user's data.
 */
@WebFilter(filterName = "PersonalInfoEditFilter")
public class PersonalInfoEditFilter implements Filter {

    /**
     * Method to filter users whose identity doesn't matches with message author identity.
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
        User user = (User) session.getAttribute(Attribute.AUTH_USER);

        if (user.getId().toString().equals(httpRequest.getParameter(Attribute.USER_ID))) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
