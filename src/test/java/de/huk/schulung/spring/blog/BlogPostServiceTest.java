package de.huk.schulung.spring.blog;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/*
 * Testfall: wenn ein BlogPost angelegt wird, wird das creationDate gesetzt
 */

class BlogPostServiceTest {

    BlogPostService service = new BlogPostService(
            mock(ApplicationEventPublisher.class)
    );

    @Test
    void shouldSetCreationDateOnCreation() {
        // Arrange / Setup / Given
        BlogPost post = new BlogPost();
        post.setTitle("Test");
        post.setContent("Content");
        // Act / Test / When
        service.createPost(post);
        // Assert / Assert / Then
        assertThat(post.getCreationDate()).isNotNull();
    }

}