package io.github.http;

import io.github.http.contracts.InteractsWithContentTypes;
import io.github.http.contracts.InteractsWithInput;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FormRequest implements InteractsWithInput, InteractsWithContentTypes, InitializingBean {

    private ParameterBag<String> headerBag;

    private ParameterBag<Object> inputBag;

    private final HttpServletRequest httpServletRequest;

    public FormRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void afterPropertiesSet() {
        headerBag = new HeaderBag(httpServletRequest);
        inputBag = new InputBag(httpServletRequest, this);
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

    public Map<String, Object> only(String[] names) {
        return null;
    }


    public Map<String, Object> except(String[] names) {
        return null;
    }

    public FormRequest merge(Map<String, Object> extra) {

        return this;
    }

    public FormRequest filter() {

        return this;
    }

    @Override
    public Object get(String name) {
        return null;
    }

    @Override
    public <T extends Object> T input(String name) {

        if (!has(name)) {
            return null;
        }


        return null;
    }

    @Override
    public boolean isJson() {

        if (!hasHeader("Content-Type")) {
            return false;
        }

        return headerBag.get("Content-Type").equals("application/json");
    }
}
