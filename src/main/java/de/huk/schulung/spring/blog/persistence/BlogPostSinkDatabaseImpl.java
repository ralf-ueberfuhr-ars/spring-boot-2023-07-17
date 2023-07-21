package de.huk.schulung.spring.blog.persistence;

import de.huk.schulung.spring.blog.domain.BlogPost;
import de.huk.schulung.spring.blog.domain.BlogPostSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlogPostSinkDatabaseImpl implements BlogPostSink {

    private final BlogPostRepository repo;

    @Override
    public Collection<BlogPost> findPosts() {
        return repo.findAll();
    }

    @Override
    public Optional<BlogPost> findPostById(long id) {
        return repo.findById(id);
    }

    @Override
    public void savePost(BlogPost newPost) {
        final var savedPost = repo.save(newPost);
        newPost.setId(savedPost.getId());
    }

    @Override
    public void deletePost(long id) {
        repo.deleteById(id);
    }
}
