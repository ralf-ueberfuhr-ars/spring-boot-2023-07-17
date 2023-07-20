package de.huk.schulung.spring.blog.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// Start mit "java -jar my-app.jar -Dspring.profiles.active=dev"
@Profile("dev")
@RequiredArgsConstructor
public class BlogPostInitializer {

    private final BlogPostService service;
    private final List<BlogPost> posts;
    // @Qualifier("webPost")
    // private final BlogPost post;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeSampleBlogPosts() {
        posts.forEach(service::createPost);
//        service.createPost(new BlogPost(null, "Spring is toll!", "lorem ipsum", null));
//        service.createPost(new BlogPost(null, "Lombok is toll!", "lorem ipsum", null));
//        service.createPost(new BlogPost(null, null, "lorem ipsum", null));
    }

}
