package github.hema.web.http;


enum Shrubbery {
    GROUND,CRAWLING,HANGING
}

public class Main {
    public static void main(String[] args) {

//        SpringApplication.run(Main.class, args);
        for (Shrubbery shrubbery : Shrubbery.values()){
            System.out.println(shrubbery);
        }
    }
}