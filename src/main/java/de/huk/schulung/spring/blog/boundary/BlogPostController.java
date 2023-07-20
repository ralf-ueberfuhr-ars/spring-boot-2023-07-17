package de.huk.schulung.spring.blog.boundary;

import de.huk.schulung.spring.blog.domain.BlogPost;
import de.huk.schulung.spring.blog.domain.BlogPostService;
import de.huk.schulung.spring.blog.domain.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Validated
@Controller
@RequestMapping("/blogposts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService service;

    @PostMapping(value = "/anlegen", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
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
