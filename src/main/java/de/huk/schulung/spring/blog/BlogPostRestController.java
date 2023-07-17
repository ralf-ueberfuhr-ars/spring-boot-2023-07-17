package de.huk.schulung.spring.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api/v1/blogposts")
public class BlogPostRestController {

    private final Collection<BlogPost> posts = new HashSet<>();

    {
    posts.add(new BlogPost(1L, "Spring is toll!", "lorem ipsum", LocalDateTime.now()));
    posts.add(new BlogPost(10L, "Lombok is toll!", "lorem ipsum", LocalDateTime.now()));
    }

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

}
