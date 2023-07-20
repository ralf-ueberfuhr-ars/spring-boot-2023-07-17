package de.huk.schulung.spring.blog.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialBlogPostConfiguration {

    @Bean
    public BlogPost post1() {
        return new BlogPost(null, "Spring ist toll!", "Lorem ipsum", null);
    }

    @Bean
    public BlogPost post2() {
        return new BlogPost(null, "Java ist toll!", "Lorem ipsum", null);
    }

}
