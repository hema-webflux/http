package io.github.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
final class HeaderBag implements ParameterBag<String>, InitializingBean {

    private final Map<String, String> headers = new HashMap<>();

    private final HttpServletRequest httpServletRequest;

    HeaderBag(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean has(String name) {
        return headers.containsKey(name);
    }

    @Override
    public String get(String name) {
        return headers.get(name);
    }

    @Override
    public Map<String, String> all() {
        return headers;
    }

    @Override
    public void afterPropertiesSet() {
        Enumeration<String> headerEnum = httpServletRequest.getParameterNames();

        while (headerEnum.hasMoreElements()) {

            String headerName = headerEnum.nextElement();

            if (!headers.containsKey(headerName)) {
                headers.put(headerName, httpServletRequest.getHeader(headerName));
            }
        }
    }
}
