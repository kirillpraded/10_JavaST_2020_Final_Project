package by.praded.ask_and_go.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * WebFilter for encoding.
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {

    /**
     * Method to set character encoding of request to `UTF-8`.
     *
     * @param request  - client request.
     * @param response - response corresponding to the request
     * @param chain    - chain of filters.
     * @throws IOException      - when appears servlet's input-output exception.
     * @throws ServletException - exception of the servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
