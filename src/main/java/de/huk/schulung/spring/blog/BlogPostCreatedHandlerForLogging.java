package de.huk.schulung.spring.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostCreatedHandlerForLogging {

    private final BlogPostCreatedLogger logger;

    @EventListener(BlogPostCreatedEvent.class)
    public void handleBlogPostCreatedEvent(BlogPostCreatedEvent event) {
        logger.logBlogPostCreated(event.newPost());
    }

}
