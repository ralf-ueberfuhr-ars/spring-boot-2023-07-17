package de.huk.schulung.spring.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogPostRestControllerTest {

    @Mock
    BlogPostService service;
    @InjectMocks
    BlogPostRestController sut;

//    @BeforeEach
//    void setup() {
//        service = Mockito.mock(BlogPostService.class);
//        sut = new BlogPostRestController(service);
//    }

    @Test
    void shouldFindByIdForwardToService() {
        // Given
        final var post = new BlogPost();
        when(service.findPostById(5))
                .thenReturn(Optional.of(post));
        // When
        final var result = sut.findBlogPostById(5);
        // Then
        assertThat(result).isSameAs(post);
        verify(service).findPostById(5);
    }

    @Test
    void shouldFindByIdThrowNoSuchElementExceptionIfNotFound() {
        // Given
        when(service.findPostById(5))
                .thenReturn(Optional.empty());
        // When + Then
        assertThatThrownBy(() -> sut.findBlogPostById(5))
                .isInstanceOf(NoSuchElementException.class);
    }

}