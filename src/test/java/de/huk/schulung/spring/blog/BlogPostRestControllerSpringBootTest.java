package de.huk.schulung.spring.blog;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BlogPostRestControllerSpringBootTest {

    @Autowired
    BlogPostRestController sut;
    @MockBean
    BlogPostService service;

    // Testfall: wenn BlogPost invalide, wird beim Versuch,
    // zu erzeugen, eine CVE geworfen und der Service NICHT
    // aufgerufen

    @Test
    void shouldNotCreateBlogPostIfInvalid() {
        BlogPost post = new BlogPost();
        assertThatThrownBy(() -> sut.createBlogPost(post))
                .isInstanceOf(ConstraintViolationException.class);
        verify(service, never()).createPost(any());
    }

}
