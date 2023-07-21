package de.huk.schulung.spring.blog.domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureTestDatabase
public class BlogPostServiceSpringBootTest {

    @Autowired
    BlogPostService service;
    @MockBean
    BlogPostCreatedLogger logger;

    @Test
    void shouldThrowValidationExceptionOnInvalidBlogPostCreation() {
        BlogPost post = new BlogPost();
        assertThatThrownBy(() -> service.createPost(post))
                .isInstanceOf(ConstraintViolationException.class);
        verify(logger, never()).logBlogPostCreated(any());
    }

    @Test
    void shouldCreateBlogPostAndNotifyLogger() {
        BlogPost post = new BlogPost();
        post.setTitle("Title");
        post.setContent("content");
        service.createPost(post);
        // read post again
        assertThat(service.getPosts())
                .hasSize(1)
                .first()
                .extracting(BlogPost::getTitle, BlogPost::getContent)
                .containsExactly("Title", "content");
        // verify logging
        verify(logger).logBlogPostCreated(post);
    }

    // TODO @RecordApplicationEvents

}
