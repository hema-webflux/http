package io.github.http.provider;

import io.github.http.FormRequest;
import io.github.http.bag.HeaderBag;
import io.github.http.bag.InputBag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FormRequestConfiguration {

    private final HttpServletRequest httpServletRequest;

    public FormRequestConfiguration(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Scope("prototype")
    public FormRequest formRequest() {

        HeaderBag headerBag = new HeaderBag(httpServletRequest);

        return new FormRequest(headerBag, new InputBag(httpServletRequest, headerBag));
    }
}
