package by.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandResponse {

    private RoutingType routingType;
    private String routingPage;
    private HttpServletRequest request;
    private HttpServletResponse response;


    public CommandResponse(RoutingType routingType, String routingPage, HttpServletRequest request, HttpServletResponse response) {
        this.routingType = routingType;
        this.routingPage = routingPage;
        this.request = request;
        this.response = response;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }

    public String getRoutingPage() {
        return routingPage;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
