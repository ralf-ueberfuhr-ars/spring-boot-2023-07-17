package de.huk.schulung.spring.blog.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    @ConditionalOnMissingBean(BlogPostSink.class)
    public BlogPostSink inMemorySink() {
        return new BlogPostSinkInMemoryImpl();
    }

}
