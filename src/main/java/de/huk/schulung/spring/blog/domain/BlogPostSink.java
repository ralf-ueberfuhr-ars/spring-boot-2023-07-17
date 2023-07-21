package de.huk.schulung.spring.blog.domain;

import java.util.Collection;
import java.util.Optional;

public interface BlogPostSink {

    Collection<BlogPost> findPosts();

    Optional<BlogPost> findPostById(long id);

    void savePost(BlogPost newPost);

    void deletePost(long id);

}
