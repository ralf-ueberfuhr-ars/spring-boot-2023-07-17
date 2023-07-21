package de.huk.schulung.spring.blog.domain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostSink sink;


    public Collection<BlogPost> getPosts() {
        return sink.findPosts();
    }

    public Optional<BlogPost> findPostById(long id) {
        return sink.findPostById(id);
    }

    @PublishEvent(BlogPostCreatedEvent.class)
    public void createPost(@Valid BlogPost newPost) {
        newPost.setCreationDate(LocalDateTime.now());
        sink.savePost(newPost);
    }

    public void deletePost(long id) {
        // TODO eigene NotFoundException werfen
        sink.deletePost(id);
    }


}
