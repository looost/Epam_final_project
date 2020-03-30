package by.training.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class AddCommentTag extends TagSupport {

    private static final long serialVersionUID = 5195052932466054307L;
    private String userLogin;
    private String actionAddComment;
    private String bundle;
    private String hrefLogin;


    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setActionAddComment(String actionAddComment) {
        this.actionAddComment = actionAddComment;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public void setHrefLogin(String hrefLogin) {
        this.hrefLogin = hrefLogin;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            System.out.println(userLogin);
            JspWriter out = pageContext.getOut();
            if (pageContext.getSession().getAttribute("user") != null) {
                out.write("<div>");
                out.write("<form action=\"" + actionAddComment + "\" method=\"post\">");
                out.write("<div class=\"form-group mt-3\">");
                out.write("<label for=\"exampleFormControlTextarea1\"><fmt:message key=\"enterComment\"\n" +
                        "                                                                                          bundle=\" " + bundle + " \"/>:</label>");
                out.write("<textarea name=\"comment\" class=\"form-control\" id=\"exampleFormControlTextarea1\"\n" +
                        "                                              rows=\"3\"\n" +
                        "                                              placeholder=\"<fmt:message key=\"maxNumberCharacters\" bundle=\" " + bundle + " \" - 512\"/></textarea>");
                out.write("</div>");
                out.write("<button type=\"submit\" class=\"btn btn-danger mb-2\"><fmt:message key=\"signIn\" bundle=\" " + bundle + " \"/></button>");
                out.write("</form>");
                out.write("</div>");
            } else {

                System.out.println("<div class=\"alert alert-danger mt-3\" role=\"alert\"> <fmt:message key=\"commentRegistrUser\" bundle=\" " + bundle + " \"/> <a\n" +
                        "                                href=\"" + hrefLogin + "\"\n" +
                        "                                class=\"alert-link\"><fmt:message key=\"signIn\" bundle=\" " + bundle + " \"/></a>.");

                out.write("<div class=\"alert alert-danger mt-3\" role=\"alert\"> <fmt:message key=\"commentRegistrUser\" bundle=\" " + bundle + " \"/> <a\n" +
                        "                                href=\"" + hrefLogin + "\"\n" +
                        "                                class=\"alert-link\"><fmt:message key=\"signIn\" bundle=\" " + bundle + " \"/></a>.");
                out.write("</div>");
            }
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }
}
