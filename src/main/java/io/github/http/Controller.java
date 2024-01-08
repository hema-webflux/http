package io.github.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Enumeration;

@RestController
public class Controller {

    @GetMapping("/")
    public void index(HttpServletRequest request) throws IOException {
        System.out.println(request.getQueryString());
        System.out.println(request.getRemoteUser());
        System.out.println(request.getRequestURI());
        System.out.println(request.getPathInfo());



        String line;


        System.out.println();

        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            System.out.print("header:");
            System.out.print(headers.nextElement());
            System.out.println();
        }

        Enumeration<String> parameters = request.getParameterNames();

        while (parameters.hasMoreElements()) {
            System.out.print("参数:");
            System.out.print(parameters.nextElement());
            System.out.println();
        }

    }
}
