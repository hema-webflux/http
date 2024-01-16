package hema.web.http;

import hema.web.http.contracts.InteractsWithContentTypes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

final class HeaderBag implements FormRequest.ParameterBag<String>, InteractsWithContentTypes {

    private final Map<String, String> headers = new HashMap<>();

    private final HttpServletRequest httpServletRequest;

    public HeaderBag(HttpServletRequest httpServletRequest) {
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


    public void afterPropertiesSet() {
        Enumeration<String> headerEnum = httpServletRequest.getParameterNames();

        while (headerEnum.hasMoreElements()) {

            String headerName = headerEnum.nextElement();

            if (!headers.containsKey(headerName)) {
                headers.put(headerName, httpServletRequest.getHeader(headerName));
            }
        }
    }

    @Override
    public boolean isJson() {
        if (httpServletRequest.getContentType() == null) {
            return false;
        }

        return httpServletRequest.getContentType().equals(MediaType.APPLICATION_JSON_VALUE);
    }
}