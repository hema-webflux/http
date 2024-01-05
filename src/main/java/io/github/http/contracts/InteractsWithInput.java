package io.github.http.contracts;

import java.util.Map;

public interface InteractsWithInput {

    boolean hasHeader(String name);

    default String bearerToken() {

        String bearerToken = header("Authorization");

        String prefix = "Bearer ";

        return bearerToken.startsWith(prefix) ? bearerToken.substring(0, prefix.length() - 1) : "";
    }

    String header(String name);

    Map<String, Object> all();

    boolean has(String name);

    <T> T get(String name);

    <T> T input(String name);

}
