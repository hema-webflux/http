package github.hema.web.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public void index( FormRequest formRequest) {

        formRequest.all().forEach((k, v) -> {
            System.out.printf("key:%s,value:%s", k, v);
        });

        formRequest.only(new String[]{"jack","a"});

        formRequest.except(new String[]{"kwg"});

       String a = formRequest.input("");

    }
}
