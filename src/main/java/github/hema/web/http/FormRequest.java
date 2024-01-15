package github.hema.web.http;

import github.hema.web.http.contracts.InteractsWithInput;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class FormRequest implements InteractsWithInput {

    private final ParameterBag<String> headerBag;

    private final ParameterBag<Object> inputBag;

    private final HttpServletRequest httpServletRequest;

    public FormRequest(HttpServletRequest httpServletRequest, ParameterBag<String> headerBag, ParameterBag<Object> inputBag) {
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

    public sealed interface ParameterBag<T> permits HeaderBag, InputBag {

        boolean has(String name);

        T get(String name);

        Map<String, T> all();
    }
}
