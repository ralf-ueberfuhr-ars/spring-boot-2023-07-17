package de.huk.schulung.spring.blog.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlogPostCreatedLogger {

    public void logBlogPostCreated(BlogPost post) {
        log.info("BlogPost created: " + post);
    }

}
