package io.github.http;

import io.github.http.contracts.InteractsWithContentTypes;
import io.github.http.contracts.InteractsWithInput;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FormRequest implements InteractsWithInput, InteractsWithContentTypes, InitializingBean {

    private ParameterBag headerBag;

    private ParameterBag inputBag;

    private final HttpServletRequest httpServletRequest;

    public FormRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        headerBag = new ParameterBag.HeaderBag(httpServletRequest);
        inputBag = new ParameterBag.InputBag(httpServletRequest);
    }

    @Override
    public boolean hasHeader(String name) {
        return inputBag.has(name);
    }

    @Override
    public String header(String name) {
        return (String) headerBag.get(name);
    }

    @Override
    public Map<String, Object> all() {
        return inputBag.all();
    }

    @Override
    public boolean has(String name) {
        return inputBag.has(name);
    }

    public FormRequest only(String[] names) {
        return null;
    }


    public FormRequest except(String[] names) {
        return null;
    }

    public FormRequest merge(Map<String, Object> extra) {

        return this;
    }

    public FormRequest filter() {

        return this;
    }

    @Override
    public <T> T get(String name) {
        return null;
    }

    @Override
    public <T extends Object> T input(String name) {

        if (!has(name)) {
            return null;
        }

        T value = (T) inputBag.get(name);


    }

    @Override
    public boolean isJson() {

        if (!hasHeader("Content-Type")) {
            return false;
        }

        return headerBag.get("Content-Type").equals("application/json");
    }
}
