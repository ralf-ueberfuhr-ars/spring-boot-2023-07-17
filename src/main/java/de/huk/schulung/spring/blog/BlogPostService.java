package de.huk.schulung.spring.blog;

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

    {
        posts.add(new BlogPost(1L, "Spring is toll!", "lorem ipsum", LocalDateTime.now()));
        posts.add(new BlogPost(2L, "Lombok is toll!", "lorem ipsum", LocalDateTime.now()));
    }

    private static long counter = 3L;

    public Collection<BlogPost> getPosts() {
        return posts;
    }

    public Optional<BlogPost> findPostById(long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

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
