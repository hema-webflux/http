package hema.web.http;

import hema.web.http.contracts.InteractsWithContentTypes;
import hema.web.http.exception.RequestParseException;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

 final class InputBag implements FormRequest.ParameterBag<Object> {

    private final HttpServletRequest httpServletRequest;

    private final InteractsWithContentTypes interactsWithContentTypes;

    private Map<String, Object> content = null;

    public InputBag(HttpServletRequest httpServletRequest, InteractsWithContentTypes interactsWithContentTypes) {
        this.httpServletRequest = httpServletRequest;
        this.interactsWithContentTypes = interactsWithContentTypes;
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


    public void afterPropertiesSet() {

        prepareFormData();

        if (interactsWithContentTypes.isJson()) {
            prepareJsonData();
        }
    }

    public void prepareFormData() {

        Enumeration<String> parameters = httpServletRequest.getParameterNames();

        if (!has()) {
            content = new HashMap<>();
        }

        while (parameters.hasMoreElements()) {

            String key = parameters.nextElement();

            content.put(key, httpServletRequest.getParameter(key));
        }

    }

    public void prepareJsonData() throws RequestParseException {
        try (BufferedReader reader = httpServletRequest.getReader()) {
            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            Map<String, Object> jsonData = new JSONObject(builder.toString()).toMap();

            if (has()) {
                content.putAll(jsonData);
            } else {
                content = jsonData;
            }

        } catch (IOException e) {
            throw new RequestParseException();
        }
    }

    private boolean has() {
        return content != null;
    }
}

