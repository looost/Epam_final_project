package by.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class is a return type for {@link Command}.
 */
public class CommandResponse {

    /**
     * Routing type.
     */
    private RoutingType routingType;
    /**
     * Page title for navigation.
     */
    private String routingPage;

    private HttpServletRequest request;
    private HttpServletResponse response;


    /**
     * Instantiates a new Command response.
     *
     * @param routingType the routing type
     * @param routingPage the routing page
     * @param request     the HttpServletRequest
     * @param response    the HttpServletResponse
     */
    public CommandResponse(RoutingType routingType, String routingPage, HttpServletRequest request, HttpServletResponse response) {
        this.routingType = routingType;
        this.routingPage = routingPage;
        this.request = request;
        this.response = response;
    }

    /**
     * Gets routing type.
     *
     * @return the routing type
     */
    public RoutingType getRoutingType() {
        return routingType;
    }

    /**
     * Gets routing page.
     *
     * @return the routing page
     */
    public String getRoutingPage() {
        return routingPage;
    }

    /**
     * Gets request.
     *
     * @return the HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Gets response.
     *
     * @return the HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return response;
    }
}
