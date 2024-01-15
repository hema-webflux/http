package github.hema.web.http.contracts;

import java.util.Map;

public interface ParameterBag<T> {

    boolean has(String name);

    T get(String name);

    Map<String, T> all();
}
