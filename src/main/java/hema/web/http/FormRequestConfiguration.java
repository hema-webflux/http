package hema.web.http;

import hema.web.http.contracts.InteractsWithContentTypes;
import hema.web.http.contracts.ParameterBag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.*;

@Configuration
public class FormRequestConfiguration {

    private final HttpServletRequest httpServletRequest;

    public FormRequestConfiguration(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Bean()
    @Lazy
    @Scope("singleton")
    public FormRequest formRequest() {
        return new FormRequest(httpServletRequest, headerBag(), inputBag());
    }

    @Bean
    @Lazy
    @Scope("singleton")
    public ParameterBag<Object> inputBag() {
        return new InputBag(httpServletRequest, headerBag());
    }

    @Bean
    @Lazy
    @Scope("singleton")
    public InteractsWithContentTypes headerBag() {
        return new HeaderBag(httpServletRequest);
    }
}
