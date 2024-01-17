package hema.web.http;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class Controller {

    private final ApplicationContext applicationContext;

    public Controller(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/")
    public void index() {
       FormRequest formRequest = applicationContext.getBean(FormRequest.class);

        System.out.println(formRequest.getClass());
    }
}
