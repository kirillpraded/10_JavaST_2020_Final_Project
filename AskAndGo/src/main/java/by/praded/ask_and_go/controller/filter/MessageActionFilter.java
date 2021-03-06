package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.util.UserValidator;

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
 * WebFilter for filtering actions on messages.
 * @see by.praded.ask_and_go.entity.Answer
 * @see by.praded.ask_and_go.entity.Question
 */
@WebFilter(filterName = "MessageActionFilter")
public class MessageActionFilter implements Filter {

    /**
     * Method for filtering requests for modification questions and answer.
     * Allow only authors of message or moderator.
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
        Long userId = Long.parseLong(request.getParameter(Attribute.USER_ID)); //айди автора ответа
        //удалить вопрос или ответ - сам пользователь или модер
        if (user.getId().equals(userId) || UserValidator.validateRole(user, Role.MODERATOR)) {
            chain.doFilter(req, resp);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
