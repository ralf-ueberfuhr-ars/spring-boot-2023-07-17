package de.huk.schulung.spring.blog.persistence;

import de.huk.schulung.spring.blog.domain.BlogPost;
import de.huk.schulung.spring.blog.domain.BlogPostSink;

import java.util.Collection;
import java.util.Optional;

// TODO weitermachen mit DB
public class BlogPostSinkDatabaseImpl implements BlogPostSink {
    @Override
    public Collection<BlogPost> findPosts() {
        return null;
    }

    @Override
    public Optional<BlogPost> findPostById(long id) {
        return Optional.empty();
    }

    @Override
    public void savePost(BlogPost newPost) {

    }

    @Override
    public void deletePost(long id) {

    }
}
