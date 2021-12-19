package com.vaibhav.parkingReservation.logUtil;

import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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

            //MDC.put("correlationId", requestCid);
        }

        try {
            // call filter(s) upstream for the real processing of the request
            chain.doFilter(req, res);
        } finally {
            // it's important to always clean the cid from the MDC,
            // this Thread goes to the pool but it's loglines would still contain the cid.
            CurrentThread.removeCorrelationId();
            //MDC.remove("CID");
        }

    }

    @Override
    public void destroy() {
        // nothing
    }
    @Override
    public void init(FilterConfig fc) throws ServletException {
        // nothing
    }
}
