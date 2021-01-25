package by.praded.ask_and_go.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 11.01.2021
 * <p>
 * WebFilter for filtering user locale.
 * @see by.praded.ask_and_go.entity.User
 */
@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {
    /**
     * Method for filtering locale. Required to define session locale.
     *
     * @param req   - client request.
     * @param resp  - response corresponding to the request
     * @param chain - chain of filters.
     * @throws IOException      - when appears servlet's input-output exception.
     * @throws ServletException - exception of the servlet.
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String locale = null;
        if (Objects.isNull(session.getAttribute("locale"))) {
            Cookie[] cookies = request.getCookies();
            //нужна условная конструкция тк из инкогнито не отправляются куки и выпадает экспшн
            if (Objects.nonNull(cookies)) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("locale")) {
                        locale = cookie.getValue();
                        break;
                    }
                }
            }
            if (Objects.nonNull(locale)) {
                session.setAttribute("locale", locale);
            } else {
                //default language - english
                session.setAttribute("locale", "en_US");
            }
        }
        chain.doFilter(req, resp);
    }
}
