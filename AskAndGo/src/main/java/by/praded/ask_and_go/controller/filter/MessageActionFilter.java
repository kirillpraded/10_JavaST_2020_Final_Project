package by.praded.ask_and_go.controller.filter;

import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.util.UserValidator;

import javax.servlet.*;
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
        User user = (User) request.getSession().getAttribute("auth_user");
        Long userId = Long.parseLong(request.getParameter("user_id")); //айди автора ответа
        //удалить вопрос или ответ - сам пользователь или модер
        //закрыть вопрос - сам пользователь
        //пометить ответ на вопрос как верный - автор вопроса
        if (user.getId().equals(userId) || UserValidator.validateRole(user, Role.MODERATOR)) {
            chain.doFilter(req, resp);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
