package io.github.http.contracts;

import java.util.Map;

public interface ParameterBag<T>  {

    abstract public boolean has(String name);

    abstract public T get(String name);

    abstract public Map<String, T> all();
}
