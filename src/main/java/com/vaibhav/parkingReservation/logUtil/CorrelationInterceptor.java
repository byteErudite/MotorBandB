package com.vaibhav.parkingReservation.logUtil;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class CorrelationInterceptor implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {

            HttpServletRequest request = (HttpServletRequest) req;
            String correlationIdInRequest = request.getHeader("correlationId");

            if (Objects.nonNull(correlationIdInRequest)) {
                CurrentThread.setCorrelationId(correlationIdInRequest);
            } else {
                CurrentThread.setCorrelationId(UUID.randomUUID().toString());
            }
        }
        try {
            chain.doFilter(req, res);
        } finally {
            CurrentThread.removeCorrelationId();
        }
    }

    @Override
    public void destroy() {
    }
    @Override
    public void init(FilterConfig fc) throws ServletException {
    }
}
