package hema.web.http.contracts;

public interface InteractsWithContentTypes extends ParameterBag<String> {
    boolean isJson();
}
