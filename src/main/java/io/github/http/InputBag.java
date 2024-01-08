package io.github.http;

import io.github.http.contracts.InteractsWithContentTypes;
import io.github.http.exception.RequestParseException;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class InputBag implements ParameterBag<Object>, InitializingBean {

    private final InteractsWithContentTypes interactsWithContentTypes;

    private final HttpServletRequest httpServletRequest;

    private Map<String, Object> content = new HashMap<>();

    InputBag(HttpServletRequest httpServletRequest, InteractsWithContentTypes interactsWithContentTypes) {
        this.interactsWithContentTypes = interactsWithContentTypes;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean has(String name) {
        return content.containsKey(name);
    }

    @Override
    public Object get(String name) {
        return content.get(name);
    }

    @Override
    public Map<String, Object> all() {
        return content;
    }

    @Override
    public void afterPropertiesSet() {

        if (interactsWithContentTypes.isJson()) {
            prepareJson();
        }


    }

    private void prepareJson() throws RequestParseException {

        try (BufferedReader reader = httpServletRequest.getReader()) {
            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content = new JSONObject(builder.toString()).toMap();
        } catch (IOException e) {
            throw new RequestParseException();
        }
    }
}
