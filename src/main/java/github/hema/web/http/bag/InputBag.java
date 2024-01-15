package github.hema.web.http.bag;

import github.hema.web.http.exception.RequestParseException;
import github.hema.web.http.contracts.InteractsWithContentTypes;
import github.hema.web.http.contracts.ParameterBag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public final class InputBag<T> implements ParameterBag<T>, InitializingBean {

    private final HttpServletRequest httpServletRequest;

    private final InteractsWithContentTypes interactsWithContentTypes;

    private Map<String, T> content = null;

    public InputBag(HttpServletRequest httpServletRequest, InteractsWithContentTypes interactsWithContentTypes) {
        this.httpServletRequest = httpServletRequest;
        this.interactsWithContentTypes = interactsWithContentTypes;
    }

    @Override
    public boolean has(String name) {
        return content.containsKey(name);
    }

    @Override
    public T get(String name) {
        return content.get(name);
    }

    @Override
    public Map<String, T> all() {
        return content;
    }

    @Override
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

            content.put(key, (T) httpServletRequest.getParameter(key));
        }

    }

    public void prepareJsonData() throws RequestParseException {
        try (BufferedReader reader = httpServletRequest.getReader()) {
            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            Map<String, T> jsonData = new JSONObject(builder.toString()).toMap();

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
