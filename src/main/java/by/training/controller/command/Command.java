package by.training.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface Command. Represents design pattern Command.
 */
public interface Command {
    /**
     * Execute command.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET/POST could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the GET/POST request
     */
    CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
