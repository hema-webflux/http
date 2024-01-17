package hema.web.http;

import hema.web.http.contracts.InteractsWithContentTypes;
import hema.web.http.contracts.InteractsWithInput;
import hema.web.http.contracts.ParameterBag;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class FormRequest  implements InteractsWithInput {

    private final InteractsWithContentTypes headerBag;

    private final ParameterBag<Object> inputBag;

    private final HttpServletRequest httpServletRequest;

    public FormRequest(HttpServletRequest httpServletRequest, InteractsWithContentTypes headerBag, ParameterBag<Object> inputBag) {
        this.httpServletRequest = httpServletRequest;
        this.headerBag = headerBag;
        this.inputBag = inputBag;
    }

    @Override
    public boolean hasHeader(String name) {
        return inputBag.has(name);
    }

    @Override
    public String header(String name) {
        return headerBag.get(name);
    }

    @Override
    public Map<String, Object> all() {
        return inputBag.all();
    }

    @Override
    public boolean has(String name) {
        return inputBag.has(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T input(String name) {
        return (T) inputBag.get(name);
    }

    public boolean isRoute(String route) {
        return getRoute().equals(route);
    }

    public String getRoute() {
        return httpServletRequest.getRequestURI();
    }

    public boolean isMethod(String method) {
        return getMethod().toLowerCase().equals(method);
    }

    public String getMethod() {
        return httpServletRequest.getMethod();
    }
}
