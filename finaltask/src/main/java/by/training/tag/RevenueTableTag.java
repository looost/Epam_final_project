package by.training.tag;

import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RevenueTableTag extends TagSupport {
    private static final long serialVersionUID = 3829163077004135850L;
    private String head;
    private int rows;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspTagException {

        try {
            List<Serial> last = ServiceFactory.getInstance().getSerialService().latestSerial(4);
            JspWriter out = pageContext.getOut();
            for (Serial s : last
            ) {
                out.write("<div class=\"card mb-3\">");
                out.write("<a href=\"${pageContext.request.contextPath}/show.html?id=" + s.getId() + "\">");
                out.write(" <img class=\"card-img-top\" src=\"" + s.getLogo() + "\" alt=\"Card image cap\">");
                out.write(" </a>");
                out.write(" <div class=\"card-body\">");
                out.write("<h5 class=\"card-title text-style\">" + s.getName() + "</h5>");
                out.write(" <p class=\"card-text text-style\"><small class=\"text-muted\">");
                out.write(s.getReleaseDate() + "</small>");
                out.write("</p>");
                out.write("</div>");
                out.write("</div>");
            }

            out.write("<table border='1'><colgroup span='2' title='title' />");
            out.write("<caption>" + Locale.getDefault().getDisplayCountry() + "</caption>");
            out.write("<thead><tr><th scope='col'>" + head + "</th></tr></thead>");
            out.write("<tbody><tr><td>");
        } catch (IOException | ServiceException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        if (rows-- > 1) {
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("</td></tr></tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
