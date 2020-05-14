package by.training.tag;

import by.training.model.Genre;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class Test extends TagSupport {

    private SearchForm searchForm;

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            JspWriter out = pageContext.getOut();
            out.write(" <div class=\"collapse show\" id=\"collapseGenres\">");
            for (Genre g : genres
            ) {
                out.write("<div class=\"custom-control custom-checkbox\">");
                System.out.println(searchForm);
                out.write("<input type=\"checkbox\" class=\"custom-control-input\" id=\"customCheck" + g.getId() + "\" value=\"" + g.getId() + "\"\n" +
                        "                               name=\"genre\"" + searchForm.getGenres().contains(g.getId()) + " ? 'checked' : ''}  >");
                out.write(" <label class=\"custom-control-label text-style\" for=\"customCheck" + g.getId() + "\">" + g.getName() + "</label>");
                out.write(" </div>");
            }
            out.write("</div>");


        } catch (ServiceException | IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;

    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
