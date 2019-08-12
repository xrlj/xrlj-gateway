package com.xrlj.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(BaseFilter.class);

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        if (null != dispatcher) {
            if (!response.isCommitted()) {
                dispatcher.forward(request, response);
            }
        }
    }

    protected String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String token = StringUtils.removeStart(authorization, "Bearer ");
        return token;
    }
}
