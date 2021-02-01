package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.controller.util.Attribute;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * WebFilter for filtering requests to pages related to authentication.
 */
@WebFilter(filterName = "LoginPageFilter")
public class LoginPageFilter implements Filter {

    /**
     * Method for filtering requests to pages related to authentication.
     * Doesn't allow authenticated users to pages related to authentication.
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
        if (session.getAttribute(Attribute.AUTH_USER) == null) {
            chain.doFilter(req, resp);
        } else {
            if (Objects.nonNull(httpRequest.getHeader("referer"))) {
                httpResponse.sendRedirect(httpRequest.getHeader("referer"));
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/app/categories");
            }
        }

    }


}
