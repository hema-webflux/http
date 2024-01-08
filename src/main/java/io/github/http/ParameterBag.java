package io.github.http;

import java.util.Map;

public interface ParameterBag<T>  {

    abstract public boolean has(String name);

    abstract public T get(String name);

    abstract public Map<String, T> all();
}
