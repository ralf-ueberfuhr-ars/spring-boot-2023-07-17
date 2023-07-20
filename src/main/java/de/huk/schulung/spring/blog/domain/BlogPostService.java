package de.huk.schulung.spring.blog.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Validated
@Service
public class BlogPostService {

    private final Collection<BlogPost> posts = new HashSet<>();


    private static long counter = 1L;

    public Collection<BlogPost> getPosts() {
        return posts;
    }

    public Optional<BlogPost> findPostById(long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PublishEvent(BlogPostCreatedEvent.class)
    public void createPost(@Valid BlogPost newPost) {
        newPost.setId(counter++);
        newPost.setCreationDate(LocalDateTime.now());
        this.posts.add(newPost);
    }

    public void deletePost(long id) {
        // TODO eigene NotFoundException werfen
        this.posts.remove(this.findPostById(id).get());
    }


}
