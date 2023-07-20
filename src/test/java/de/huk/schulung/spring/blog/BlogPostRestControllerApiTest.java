package de.huk.schulung.spring.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Tests with HTTP layer
@SpringBootTest
@AutoConfigureMockMvc
public class BlogPostRestControllerApiTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    BlogPostService service;

    // Testfall: wenn BlogPost invalide, wird beim Versuch,
    // zu erzeugen, eine CVE geworfen und der Service NICHT
    // aufgerufen

    @Test
    void shouldNotCreateBlogPostIfInvalid() throws Exception {
        mvc.perform(
                post("/api/v1/blogposts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "t",
                                    "content": "content"
                                }
                                """)
        ).andExpect(
                status().isBadRequest()
        );
        verify(service, never()).createPost(any());
    }

}
