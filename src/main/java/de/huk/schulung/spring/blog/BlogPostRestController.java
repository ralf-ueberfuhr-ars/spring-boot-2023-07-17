package de.huk.schulung.spring.blog;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
                .get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost newPost) {
        newPost.setId(counter++);
        newPost.setCreationDate(LocalDateTime.now());
        this.posts.add(newPost);
        final var location = linkTo(
                methodOn(BlogPostRestController.class)
                        .findBlogPostById(newPost.getId())
        ).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") long id) {
        this.posts.remove(this.findBlogPostById(id));
    }

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
