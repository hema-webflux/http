package io.github.http;

import io.github.http.contracts.InteractsWithInput;
import io.github.http.contracts.ParameterBag;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class FormRequest implements InteractsWithInput {

    private final ParameterBag<String> headerBag;

    private final ParameterBag<Object> inputBag;

    public FormRequest(ParameterBag<String> headerBag, ParameterBag<Object> inputBag) {
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

    public Map<String, Object> only(String[] names) {

        List<String> keys = Arrays.asList(names);

        return inputBag.all().entrySet().stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Object> except(String[] names) {
        List<String> keys = Arrays.asList(names);

        return inputBag.all().entrySet().stream()
                .filter(entry -> !keys.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public FormRequest merge(Map<String, Object> extra) {

        return this;
    }

    public FormRequest filter() {

        return this;
    }

    @Override
    public <T extends Object> T input(String name) {

        if (!has(name)) {
            return null;
        }


        return null;
    }
}
