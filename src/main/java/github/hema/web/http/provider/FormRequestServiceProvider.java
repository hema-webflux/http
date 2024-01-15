package github.hema.web.http.provider;

import github.hema.web.http.bag.HeaderBag;
import github.hema.web.http.bag.InputBag;
import github.hema.web.http.FormRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FormRequestServiceProvider {

    private final HttpServletRequest httpServletRequest;

    public FormRequestServiceProvider(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Scope("prototype")
    public FormRequest formRequest() {

        HeaderBag headerBag = new HeaderBag(httpServletRequest);

        return new FormRequest(headerBag, new InputBag(httpServletRequest, headerBag));
    }
}
