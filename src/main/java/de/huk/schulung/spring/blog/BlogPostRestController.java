package de.huk.schulung.spring.blog;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Validated // non-REST: for validating request parameters instead of request bodies
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
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@Valid @RequestBody BlogPost newPost) {
        newPost.setId(counter++);
        newPost.setCreationDate(LocalDateTime.now());
        this.posts.add(newPost);
        return newPost;
        // REST: HTTP 201 Created mit Location-Header -> ResponseEntity
    }

    // DELETE? -> DELETE /api/v1/blogposts/3 -> 204 (leerer Body)


    // KEIN REST!!!
    @PostMapping(value = "/anlegen", produces = MediaType.TEXT_PLAIN_VALUE)
    public String createBlogPostOhneRest(
            @RequestParam("content") String content,
            @RequestParam("title") @Title String title
    ) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setCreationDate(LocalDateTime.now());
        newPost.setId(counter++);
        posts.add(newPost);
        return newPost.toString();
    }

}
