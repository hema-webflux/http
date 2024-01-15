package github.hema.web.http;

import github.hema.web.http.contracts.InteractsWithContentTypes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FormRequestConfiguration {

    private final HttpServletRequest httpServletRequest;

    public FormRequestConfiguration(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Bean
    @Scope("property")
    public FormRequest formRequest() {
        return new FormRequest(httpServletRequest, headerBag(), inputBag());
    }

    @Bean
    @Scope("property")
    public FormRequest.ParameterBag<Object> inputBag() {
        return new InputBag(httpServletRequest, (InteractsWithContentTypes) headerBag());
    }

    @Bean
    @Scope("property")
    public FormRequest.ParameterBag<String> headerBag() {
        return new HeaderBag(httpServletRequest);
    }
}
