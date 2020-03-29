package by.training.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Test extends TagSupport {

    private static final long serialVersionUID = 3506709197588869296L;

    private String bundle;

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
