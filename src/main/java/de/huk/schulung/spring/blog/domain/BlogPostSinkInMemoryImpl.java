package de.huk.schulung.spring.blog.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class BlogPostSinkInMemoryImpl implements BlogPostSink {

    private final Collection<BlogPost> posts = new HashSet<>();
    private static long counter = 1L;

    @Override
    public Collection<BlogPost> findPosts() {
        return posts;
    }

    @Override
    public Optional<BlogPost> findPostById(long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void savePost(BlogPost newPost) {
        newPost.setId(counter++);
        this.posts.add(newPost);
    }

    @Override
    public void deletePost(long id) {
        this.posts.remove(this.findPostById(id).get());
    }
}
