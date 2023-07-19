package de.huk.schulung.spring.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    /*
     * Wann MUSS ich diese Variante verwenden?
     *  - fremde Klassen registrieren
     *  - Objekt wird anders "erzeugt"
     *    - kein Konstruktor, sondern statische Methode (Factory Pattern)
     *    - Objekt wird nicht erzeugt, sondern kommt aus einer anderen Quelle
     */

//    @Validated
//    @Bean
//    public BlogPostService myService() {
//        return new BlogPostService();
//    }

    /*
     * Beispiel: Convention over Configuration
     *  - SPRING: 1 Objekt vom Typ WebMvcConfigurer im Context
     */

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/")
                        .setViewName("redirect:/gelbekatze.html");
            }
        };
    }

    @Bean
    public BlogPost webPost() {
        return new BlogPost(null, "HTTP-Wissen wird wichtiger!", "Lorem ipsum", null);
    }


}
