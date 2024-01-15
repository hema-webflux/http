package github.hema.web.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public void index(HttpServletRequest httpServletRequest, FormRequest formRequest) {

        String name = formRequest.input("string");

        System.out.println(name);

    }
}
