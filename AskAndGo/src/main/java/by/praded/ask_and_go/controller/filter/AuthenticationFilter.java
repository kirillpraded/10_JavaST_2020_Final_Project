package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.controller.util.Attribute;

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
 * WebFilter for filter users non-authenticated users.
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    /**
     * Method to filter non-authenticated users.
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

        if (session.getAttribute(Attribute.AUTH_USER) != null) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            session.setAttribute("referer", httpRequest.getHeader("referer"));
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }

    }
}
