package de.huk.schulung.spring.blog;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class BlogPostServiceSpringBootTest {

    @Autowired
    BlogPostService service;

    @Test
    void shouldThrowValidationExceptionOnInvalidBlogPostCreation() {
        BlogPost post = new BlogPost();
        assertThatThrownBy(() ->  service.createPost(post))
                .isInstanceOf(ConstraintViolationException.class);
    }

}
