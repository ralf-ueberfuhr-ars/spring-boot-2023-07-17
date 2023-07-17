package de.huk.schulung.spring.blog;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/api/v1/blogposts")
public class BlogPostRestController {

    private final Collection<BlogPost> posts = new HashSet<>();

    {
    posts.add(new BlogPost(1L, "Spring is toll!", "lorem ipsum", LocalDateTime.now()));
    posts.add(new BlogPost(2L, "Lombok is toll!", "lorem ipsum", LocalDateTime.now()));
    }

    private static long counter = 3L;

    @GetMapping
    public Collection<BlogPost> readAllBlogPosts() {
        return posts;
    }

    @GetMapping("/{id}")
    public BlogPost findBlogPostById(@PathVariable("id") long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get(); // TODO NoSuchElementException -> 404
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BlogPost createBlogPost(@RequestBody BlogPost newPost) {
        newPost.setId(counter++);
        this.posts.add(newPost);
        return newPost;
        // REST: HTTP 201 Created mit Location-Header
    }

}
