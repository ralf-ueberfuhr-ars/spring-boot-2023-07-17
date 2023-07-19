package de.huk.schulung.spring.blog;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated // non-REST: for validating request parameters instead of request bodies
@RestController
@RequestMapping("/api/v1/blogposts")
@RequiredArgsConstructor
public class BlogPostRestController {

    private final BlogPostService service;

    @GetMapping
    public Collection<BlogPost> readAllBlogPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    public BlogPost findBlogPostById(@PathVariable("id") long id) {
        return service.findPostById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost newPost) {
        service.createPost(newPost);
        final var location = linkTo(
                methodOn(BlogPostRestController.class)
                        .findBlogPostById(newPost.getId())
        ).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") long id) {
        service.deletePost(id);
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
        service.createPost(newPost);
        return newPost.toString();
    }

}
