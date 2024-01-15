package github.hema.web.http;

import github.hema.web.http.contracts.InteractsWithInput;
import github.hema.web.http.contracts.ParameterBag;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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

    @Override
    public <T> T input(String name)  {

        Class<T> value = (Class<T>) inputBag.get(name);

        return null;
    }
}
