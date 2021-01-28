package by.praded.ask_and_go.tags;

import by.praded.ask_and_go.controller.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kiryl Praded
 * 28.12.2020
 */
public class ErrorTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(ErrorTag.class);

    private Integer code;


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String locale = (String) pageContext.getSession().getAttribute(Attribute.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle("property.text", new Locale(locale));
        String message;
        if (code.equals(404)) {
            message = bundle.getString("error-page.not-found");
        } else if (code.equals(403)) {
            message = bundle.getString("error-page.not-allowed");
        } else {
            message = bundle.getString("error-page.server");
        }
        try {
            out.write("<h2>" + code + "</h2>");
            out.write("<p>" + message + "</p>");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
