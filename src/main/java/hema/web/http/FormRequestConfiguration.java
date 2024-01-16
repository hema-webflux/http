package hema.web.http;

import hema.web.http.contracts.InteractsWithContentTypes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormRequestConfiguration {

    private final HttpServletRequest httpServletRequest;

    public FormRequestConfiguration(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Bean
    public FormRequest formRequest() {
        return new FormRequest(httpServletRequest, headerBag(), inputBag());
    }

    @Bean
    public FormRequest.ParameterBag<Object> inputBag() {
        return new InputBag(httpServletRequest, (InteractsWithContentTypes) headerBag());
    }

    @Bean
    public FormRequest.ParameterBag<String> headerBag() {
        return new HeaderBag(httpServletRequest);
    }
}
